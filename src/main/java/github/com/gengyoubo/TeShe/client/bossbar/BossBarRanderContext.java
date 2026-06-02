package github.com.gengyoubo.TeShe.client.bossbar;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.util.Mth;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class BossBarRanderContext
{
    private final ResourceLocation texture;
    private Layout layout;

    public BossBarRanderContext(ResourceLocation texture)
    {
        this.texture = texture;
    }

    public int render(GuiGraphics graphics, Font font, Component name, int screenWidth, int y, float progress) throws IOException
    {
        Layout currentLayout = getLayout();
        int decorationX = (screenWidth - currentLayout.decoration.width) / 2;
        int decorationY = y;

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        drawRect(graphics, currentLayout.decoration, decorationX, decorationY, currentLayout.textureWidth, currentLayout.textureHeight);

        if (currentLayout.bar != null) {
            int frameCenterX = decorationX + currentLayout.frame.centerX() - currentLayout.decoration.u;
            int frameCenterY = decorationY + currentLayout.frame.centerY() - currentLayout.decoration.v;
            int barX = frameCenterX - currentLayout.bar.width / 2;
            int barY = frameCenterY - currentLayout.bar.height / 2;
            int filledWidth = Mth.ceil(Mth.clamp(progress, 0.0F, 1.0F) * currentLayout.bar.width);
            if (filledWidth > 0) {
                graphics.blit(
                        texture,
                        barX,
                        barY,
                        (float)currentLayout.bar.u,
                        (float)currentLayout.bar.v,
                        filledWidth,
                        currentLayout.bar.height,
                        currentLayout.textureWidth,
                        currentLayout.textureHeight
                );
            }
        }

        RenderSystem.disableBlend();

        int titleX = screenWidth / 2 - font.width(name) / 2;
        graphics.drawString(font, name, titleX, y - 9, 0xFFFFFF);
        return currentLayout.decoration.height + 2;
    }

    public boolean isSeparable() throws IOException
    {
        return getLayout().bar != null;
    }

    private void drawRect(GuiGraphics graphics, TextureRect rect, int x, int y, int textureWidth, int textureHeight)
    {
        graphics.blit(
                texture,
                x,
                y,
                (float)rect.u,
                (float)rect.v,
                rect.width,
                rect.height,
                textureWidth,
                textureHeight
        );
    }

    private Layout getLayout() throws IOException
    {
        if (layout == null) {
            layout = loadLayout();
        }

        return layout;
    }

    private Layout loadLayout() throws IOException
    {
        Resource resource = Minecraft.getInstance().getResourceManager().getResource(texture)
                .orElseThrow(() -> new IOException("Missing boss bar texture: " + texture));
        try (InputStream inputStream = resource.open();
             NativeImage image = NativeImage.read(inputStream)) {
            List<TextureRect> segments = findNonEmptyColumnSegments(image);
            if (segments.isEmpty()) {
                throw new IOException("Boss bar texture has no visible pixels: " + texture);
            }

            TextureRect decoration = segments.get(0);
            TextureRect bar = segments.size() > 1 ? segments.get(1) : null;
            TextureRect frame = findFrameRect(image, decoration);
            return new Layout(image.getWidth(), image.getHeight(), decoration, frame, bar);
        }
    }

    private static List<TextureRect> findNonEmptyColumnSegments(NativeImage image)
    {
        List<TextureRect> segments = new ArrayList<>();
        boolean inSegment = false;
        int startX = 0;

        for (int x = 0; x < image.getWidth(); x++) {
            boolean hasVisiblePixel = hasVisiblePixelInColumn(image, x);
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

    private static TextureRect findFrameRect(NativeImage image, TextureRect decoration)
    {
        int threshold = Math.max(8, decoration.width / 4);
        TextureRect best = null;
        int bestRunLength = 0;

        for (int y = decoration.v; y < decoration.v + decoration.height; y++) {
            int runStart = -1;
            for (int x = decoration.u; x < decoration.u + decoration.width; x++) {
                boolean visible = hasVisiblePixel(image, x, y);
                if (visible && runStart < 0) {
                    runStart = x;
                }

                if ((!visible || x == decoration.u + decoration.width - 1) && runStart >= 0) {
                    int runEnd = visible && x == decoration.u + decoration.width - 1 ? x : x - 1;
                    int runLength = runEnd - runStart + 1;
                    if (runLength >= threshold && runLength > bestRunLength) {
                        best = new TextureRect(runStart, y, runLength, 1);
                        bestRunLength = runLength;
                    }
                    runStart = -1;
                }
            }
        }

        if (best == null) {
            return decoration;
        }

        int frameMinX = best.u;
        int frameMaxX = best.u + best.width - 1;
        int frameMinY = best.v;
        int frameMaxY = best.v;

        for (int y = decoration.v; y < decoration.v + decoration.height; y++) {
            int rowMinX = Integer.MAX_VALUE;
            int rowMaxX = Integer.MIN_VALUE;
            for (int x = frameMinX; x <= frameMaxX; x++) {
                if (hasVisiblePixel(image, x, y)) {
                    rowMinX = Math.min(rowMinX, x);
                    rowMaxX = Math.max(rowMaxX, x);
                }
            }

            if (rowMinX <= rowMaxX && rowMinX <= frameMaxX && rowMaxX >= frameMinX) {
                frameMinY = Math.min(frameMinY, y);
                frameMaxY = Math.max(frameMaxY, y);
            }
        }

        return new TextureRect(frameMinX, frameMinY, frameMaxX - frameMinX + 1, frameMaxY - frameMinY + 1);
    }

    private static TextureRect boundsForColumns(NativeImage image, int startX, int endX)
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

        return new TextureRect(startX, minY, endX - startX + 1, maxY - minY + 1);
    }

    private static boolean hasVisiblePixelInColumn(NativeImage image, int x)
    {
        for (int y = 0; y < image.getHeight(); y++) {
            if (hasVisiblePixel(image, x, y)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasVisiblePixel(NativeImage image, int x, int y)
    {
        return ((image.getPixelRGBA(x, y) >>> 24) & 0xFF) != 0;
    }

    private static final class Layout
    {
        private final int textureWidth;
        private final int textureHeight;
        private final TextureRect decoration;
        private final TextureRect frame;
        private final TextureRect bar;

        private Layout(int textureWidth, int textureHeight, TextureRect decoration, TextureRect frame, TextureRect bar)
        {
            this.textureWidth = textureWidth;
            this.textureHeight = textureHeight;
            this.decoration = decoration;
            this.frame = frame;
            this.bar = bar;
        }
    }

    private static final class TextureRect
    {
        private final int u;
        private final int v;
        private final int width;
        private final int height;

        private TextureRect(int u, int v, int width, int height)
        {
            this.u = u;
            this.v = v;
            this.width = width;
            this.height = height;
        }

        private int centerX()
        {
            return u + width / 2;
        }

        private int centerY()
        {
            return v + height / 2;
        }
    }
}
