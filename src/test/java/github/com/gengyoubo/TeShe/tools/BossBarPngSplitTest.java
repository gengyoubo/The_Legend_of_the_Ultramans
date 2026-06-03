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
        File file = new File(args.length > 0 ? args[0] : "src/main/resources/assets/teshe/textures/gui/boss_bar/cosmic_bullibard_boss.png");
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
        List<Rect> rowSegments = findRowSeparatedSegments(image);
        writeRowSegments(image, rowSegments, outputDir);
        Anchor anchor = rowSegments.size() > 1 ? findBarAnchor(image, rowSegments.get(0)) : null;
        if (anchor != null) {
            writeOverlayPreview(image, rowSegments.get(0), rowSegments.get(1), anchor, outputDir);
        }

        System.out.println("PNG: " + file.getAbsolutePath());
        System.out.println("size: " + image.getWidth() + "x" + image.getHeight());
        System.out.println("column threshold: " + columnThreshold);
        System.out.println("output: " + outputDir.getAbsolutePath());
        System.out.println("row segments: " + rowSegments.size());
        for (int i = 0; i < rowSegments.size(); i++) {
            System.out.println("  row #" + (i + 1) + " " + rowSegments.get(i));
        }
        if (anchor != null) {
            System.out.println("bar anchor: " + anchor);
            System.out.println("overlay: segment_03_overlay_preview.png");
        }
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

    private static List<Rect> findRowSeparatedSegments(BufferedImage image)
    {
        List<Rect> rowSegments = new ArrayList<>();
        int y = 0;

        while (y < image.getHeight()) {
            while (y < image.getHeight() && countVisiblePixelsInRow(image, y) == 0) {
                y++;
            }

            if (y >= image.getHeight()) {
                break;
            }

            int startY = y;
            int endY = image.getHeight() - 1;
            for (; y < image.getHeight(); y++) {
                if (countVisiblePixelsInRow(image, y) == 0) {
                    endY = y;
                    break;
                }
            }

            int maxX = -1;
            for (int scanY = startY; scanY <= endY; scanY++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    if (hasVisiblePixel(image, x, scanY)) {
                        maxX = Math.max(maxX, x);
                    }
                }
            }

            if (maxX >= 0) {
                rowSegments.add(new Rect(0, startY, maxX + 1, endY - startY + 1));
            }

            y = endY + 1;
        }

        return rowSegments;
    }

    private static void writeRowSegments(BufferedImage image, List<Rect> segments, File outputDir) throws IOException
    {
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new IOException("Unable to create output directory: " + outputDir.getAbsolutePath());
        }

        for (int i = 0; i < segments.size(); i++) {
            Rect segment = segments.get(i);
            BufferedImage segmentImage = new BufferedImage(segment.width, segment.height, BufferedImage.TYPE_INT_ARGB);
            for (int y = 0; y < segment.height; y++) {
                for (int x = 0; x < segment.width; x++) {
                    segmentImage.setRGB(x, y, image.getRGB(segment.x + x, segment.y + y));
                }
            }

            String role = i == 0 ? "top" : i == 1 ? "bar" : "extra";
            File segmentFile = new File(outputDir, String.format("segment_%02d_%s.png", i + 1, role));
            ImageIO.write(segmentImage, "PNG", segmentFile);
        }
    }

    private static Anchor findBarAnchor(BufferedImage image, Rect decoration)
    {
        int y = decoration.y + 11;
        if (y + 1 >= decoration.y + decoration.height) {
            return null;
        }

        for (int x = decoration.x; x < decoration.x + decoration.width - 1; x++) {
            if (hasVisiblePixel(image, x, y)
                    && hasVisiblePixel(image, x + 1, y)
                    && hasVisiblePixel(image, x, y + 1)
                    && !hasVisiblePixel(image, x + 1, y + 1)) {
                return new Anchor(x, y, x + 1, y + 1);
            }
        }

        return null;
    }

    private static void writeOverlayPreview(BufferedImage image, Rect decoration, Rect bar, Anchor anchor, File outputDir) throws IOException
    {
        int previewWidth = Math.max(decoration.width, anchor.targetX - decoration.x + bar.width);
        int previewHeight = Math.max(decoration.height, anchor.targetY - decoration.y + bar.height);
        BufferedImage preview = new BufferedImage(previewWidth, previewHeight, BufferedImage.TYPE_INT_ARGB);

        drawRect(image, preview, bar, anchor.targetX - decoration.x, anchor.targetY - decoration.y);
        drawRect(image, preview, decoration, 0, 0);
        ImageIO.write(preview, "PNG", new File(outputDir, "segment_03_overlay_preview.png"));
    }

    private static void drawRect(BufferedImage source, BufferedImage target, Rect rect, int targetX, int targetY)
    {
        for (int y = 0; y < rect.height; y++) {
            for (int x = 0; x < rect.width; x++) {
                int argb = source.getRGB(rect.x + x, rect.y + y);
                if (((argb >>> 24) & 0xFF) != 0) {
                    target.setRGB(targetX + x, targetY + y, argb);
                }
            }
        }
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

    private static final class Anchor
    {
        private final int patternX;
        private final int patternY;
        private final int targetX;
        private final int targetY;

        private Anchor(int patternX, int patternY, int targetX, int targetY)
        {
            this.patternX = patternX;
            this.patternY = patternY;
            this.targetX = targetX;
            this.targetY = targetY;
        }

        @Override
        public String toString()
        {
            return "pattern=(" + patternX + "," + patternY + "), target=(" + targetX + "," + targetY + ")";
        }
    }
}
