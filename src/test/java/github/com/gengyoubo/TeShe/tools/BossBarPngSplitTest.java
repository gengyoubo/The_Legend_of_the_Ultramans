package github.com.gengyoubo.TeShe.tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class BossBarPngSplitTest
{
    private BossBarPngSplitTest()
    {
    }

    public static void main(String[] args) throws IOException
    {
        File file = new File(args.length > 0 ? args[0] : "src/main/resources/assets/teshe/textures/gui/boss_bar/img.png");
        int columnThreshold = Integer.getInteger("bossBarColumnThreshold", 0);
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            throw new IOException("Unable to read PNG: " + file.getAbsolutePath());
        }

        List<Rect> segments = findNonEmptyColumnSegments(image, columnThreshold);
        File outputDir = args.length > 1
                ? new File(args[1])
                : new File(file.getParentFile(), stripPngExtension(file.getName()) + "_split");
        cleanOutputDirectory(outputDir);
        Rect topSegment = findTopSegment(image);
        writeTopSegment(image, topSegment, outputDir);

        System.out.println("PNG: " + file.getAbsolutePath());
        System.out.println("size: " + image.getWidth() + "x" + image.getHeight());
        System.out.println("column threshold: " + columnThreshold);
        System.out.println("output: " + outputDir.getAbsolutePath());
        System.out.println("top segment: " + topSegment);
        System.out.println("segments: " + segments.size());
        for (int i = 0; i < segments.size(); i++) {
            Rect segment = segments.get(i);
            System.out.println("  #" + (i + 1) + " " + segment);
        }

        if (segments.size() < 2) {
            System.out.println("result: NOT_SEPARABLE");
            System.out.println("reason: the image has fewer than two non-empty X-column segments separated by fully transparent columns.");
            return;
        }

        Rect decoration = segments.get(0);
        Rect bar = segments.get(1);
        Rect frame = findFrameRect(image, decoration);
        System.out.println("result: SEPARABLE");
        System.out.println("decoration: " + decoration);
        System.out.println("frame: " + frame + " center=(" + frame.centerX() + "," + frame.centerY() + ")");
        System.out.println("bar: " + bar + " center=(" + bar.centerX() + "," + bar.centerY() + ")");
        System.out.println("bar draw offset in decoration: x=" + (frame.centerX() - decoration.x - bar.width / 2)
                + ", y=" + (frame.centerY() - decoration.y - bar.height / 2));
    }

    private static Rect findTopSegment(BufferedImage image)
    {
        int firstEmptyY = image.getHeight() - 1;
        for (int y = 0; y < image.getHeight(); y++) {
            if (countVisiblePixelsInRow(image, y) == 0) {
                firstEmptyY = y;
                break;
            }
        }

        int maxX = -1;
        for (int y = 0; y <= firstEmptyY; y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (hasVisiblePixel(image, x, y)) {
                    maxX = Math.max(maxX, x);
                }
            }
        }

        if (maxX < 0) {
            return new Rect(0, 0, 1, 1);
        }

        return new Rect(0, 0, maxX + 1, firstEmptyY + 1);
    }

    private static void writeTopSegment(BufferedImage image, Rect segment, File outputDir) throws IOException
    {
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new IOException("Unable to create output directory: " + outputDir.getAbsolutePath());
        }

        BufferedImage segmentImage = new BufferedImage(segment.width, segment.height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < segment.height; y++) {
            for (int x = 0; x < segment.width; x++) {
                segmentImage.setRGB(x, y, image.getRGB(segment.x + x, segment.y + y));
            }
        }

        ImageIO.write(segmentImage, "PNG", new File(outputDir, "segment_01_top.png"));
    }

    private static void cleanOutputDirectory(File outputDir) throws IOException
    {
        if (!outputDir.exists()) {
            return;
        }

        File[] files = outputDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (!file.delete()) {
                throw new IOException("Unable to delete old split PNG: " + file.getAbsolutePath());
            }
        }
    }

    private static void writeSegments(BufferedImage image, List<Rect> segments, File outputDir) throws IOException
    {
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new IOException("Unable to create output directory: " + outputDir.getAbsolutePath());
        }

        for (int i = 0; i < segments.size(); i++) {
            Rect segment = segments.get(i);
            BufferedImage segmentImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
            for (int y = segment.y; y < segment.y + segment.height; y++) {
                for (int x = segment.x; x < segment.x + segment.width; x++) {
                    segmentImage.setRGB(x, y, image.getRGB(x, y));
                }
            }

            String role = i == 0 ? "decoration" : i == 1 ? "bar" : "extra";
            File segmentFile = new File(outputDir, String.format("segment_%02d_%s.png", i + 1, role));
            ImageIO.write(segmentImage, "PNG", segmentFile);
        }
    }

    private static String stripPngExtension(String name)
    {
        return name.toLowerCase().endsWith(".png") ? name.substring(0, name.length() - 4) : name;
    }

    private static List<Rect> findNonEmptyColumnSegments(BufferedImage image, int columnThreshold)
    {
        List<Rect> segments = new ArrayList<>();
        boolean inSegment = false;
        int startX = 0;

        for (int x = 0; x < image.getWidth(); x++) {
            boolean hasVisiblePixel = countVisiblePixelsInColumn(image, x) > columnThreshold;
            if (hasVisiblePixel && !inSegment) {
                startX = x;
                inSegment = true;
            }

            if ((!hasVisiblePixel || x == image.getWidth() - 1) && inSegment) {
                int endX = hasVisiblePixel && x == image.getWidth() - 1 ? x : x - 1;
                segments.add(boundsForColumns(image, startX, endX));
                inSegment = false;
            }
        }

        return segments;
    }

    private static Rect findFrameRect(BufferedImage image, Rect decoration)
    {
        int threshold = Math.max(8, decoration.width / 4);
        Rect best = null;
        int bestRunLength = 0;

        for (int y = decoration.y; y < decoration.y + decoration.height; y++) {
            int runStart = -1;
            for (int x = decoration.x; x < decoration.x + decoration.width; x++) {
                boolean visible = hasVisiblePixel(image, x, y);
                if (visible && runStart < 0) {
                    runStart = x;
                }

                if ((!visible || x == decoration.x + decoration.width - 1) && runStart >= 0) {
                    int runEnd = visible && x == decoration.x + decoration.width - 1 ? x : x - 1;
                    int runLength = runEnd - runStart + 1;
                    if (runLength >= threshold && runLength > bestRunLength) {
                        best = new Rect(runStart, y, runLength, 1);
                        bestRunLength = runLength;
                    }
                    runStart = -1;
                }
            }
        }

        if (best == null) {
            return decoration;
        }

        int frameMinX = best.x;
        int frameMaxX = best.x + best.width - 1;
        int frameMinY = best.y;
        int frameMaxY = best.y;

        for (int y = decoration.y; y < decoration.y + decoration.height; y++) {
            boolean intersects = false;
            for (int x = frameMinX; x <= frameMaxX; x++) {
                if (hasVisiblePixel(image, x, y)) {
                    intersects = true;
                    break;
                }
            }

            if (intersects) {
                frameMinY = Math.min(frameMinY, y);
                frameMaxY = Math.max(frameMaxY, y);
            }
        }

        return new Rect(frameMinX, frameMinY, frameMaxX - frameMinX + 1, frameMaxY - frameMinY + 1);
    }

    private static Rect boundsForColumns(BufferedImage image, int startX, int endX)
    {
        int minY = image.getHeight();
        int maxY = -1;
        for (int x = startX; x <= endX; x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (hasVisiblePixel(image, x, y)) {
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        return new Rect(startX, minY, endX - startX + 1, maxY - minY + 1);
    }

    private static int countVisiblePixelsInColumn(BufferedImage image, int x)
    {
        int count = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            if (hasVisiblePixel(image, x, y)) {
                count++;
            }
        }

        return count;
    }

    private static boolean hasVisiblePixel(BufferedImage image, int x, int y)
    {
        return ((image.getRGB(x, y) >>> 24) & 0xFF) != 0;
    }

    private static int countVisiblePixelsInRow(BufferedImage image, int y)
    {
        int count = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            if (hasVisiblePixel(image, x, y)) {
                count++;
            }
        }

        return count;
    }

    private static final class Rect
    {
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        private Rect(int x, int y, int width, int height)
        {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        private int centerX()
        {
            return x + width / 2;
        }

        private int centerY()
        {
            return y + height / 2;
        }

        @Override
        public String toString()
        {
            return "x=" + x + ", y=" + y + ", w=" + width + ", h=" + height;
        }
    }
}
