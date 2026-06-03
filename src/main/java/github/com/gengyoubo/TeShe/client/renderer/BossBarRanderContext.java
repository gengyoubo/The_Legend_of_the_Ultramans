package github.com.gengyoubo.TeShe.client.renderer;

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
        if (currentLayout.bar != null) {
            int barX = decorationX + currentLayout.barAnchor.u - currentLayout.decoration.u;
            int barY = decorationY + currentLayout.barAnchor.v - currentLayout.decoration.v;
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
        drawRect(graphics, currentLayout.decoration, decorationX, decorationY, currentLayout.textureWidth, currentLayout.textureHeight);

        RenderSystem.disableBlend();

        int titleX = screenWidth / 2 - font.width(name) / 2;
        graphics.drawString(font, name, titleX, y - 9, 0xFFFFFF);
        return currentLayout.renderHeight + 2;
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
            List<TextureRect> segments = findRowSeparatedSegments(image);
            if (segments.isEmpty()) {
                throw new IOException("Boss bar texture has no visible pixels: " + texture);
            }

            TextureRect decoration = segments.get(0);
            TextureRect bar = segments.size() > 1 ? segments.get(1) : null;
            TexturePoint barAnchor = bar == null ? null : findBarAnchor(image, decoration);
            int renderHeight = decoration.height;
            if (bar != null && barAnchor != null) {
                renderHeight = Math.max(renderHeight, barAnchor.v - decoration.v + bar.height);
            }
            return new Layout(image.getWidth(), image.getHeight(), decoration, bar, barAnchor, renderHeight);
        }
    }

    private static List<TextureRect> findRowSeparatedSegments(NativeImage image)
    {
        List<TextureRect> segments = new ArrayList<>();
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
                segments.add(new TextureRect(0, startY, maxX + 1, endY - startY + 1));
            }

            y = endY + 1;
        }

        return segments;
    }

    private static TexturePoint findBarAnchor(NativeImage image, TextureRect decoration) throws IOException
    {
        TexturePoint absoluteAnchor = findBarAnchorAtY(image, decoration, 11);
        if (absoluteAnchor != null) {
            return absoluteAnchor;
        }

        TexturePoint relativeAnchor = findBarAnchorAtY(image, decoration, decoration.v + 11);
        if (relativeAnchor != null) {
            return relativeAnchor;
        }

        throw new IOException("Unable to locate boss bar anchor in texture");
    }

    private static TexturePoint findBarAnchorAtY(NativeImage image, TextureRect decoration, int y)
    {
        if (y < decoration.v || y + 1 >= decoration.v + decoration.height) {
            return null;
        }

        for (int x = decoration.u; x < decoration.u + decoration.width - 1; x++) {
            if (hasVisiblePixel(image, x, y)
                    && hasVisiblePixel(image, x + 1, y)
                    && hasVisiblePixel(image, x, y + 1)
                    && !hasVisiblePixel(image, x + 1, y + 1)) {
                return new TexturePoint(x + 1, y + 1);
            }
        }

        return null;
    }

    private static int countVisiblePixelsInRow(NativeImage image, int y)
    {
        int count = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            if (hasVisiblePixel(image, x, y)) {
                count++;
            }
        }

        return count;
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
        private final TextureRect bar;
        private final TexturePoint barAnchor;
        private final int renderHeight;

        private Layout(int textureWidth, int textureHeight, TextureRect decoration, TextureRect bar, TexturePoint barAnchor, int renderHeight)
        {
            this.textureWidth = textureWidth;
            this.textureHeight = textureHeight;
            this.decoration = decoration;
            this.bar = bar;
            this.barAnchor = barAnchor;
            this.renderHeight = renderHeight;
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
    }

    private static final class TexturePoint
    {
        private final int u;
        private final int v;

        private TexturePoint(int u, int v)
        {
            this.u = u;
            this.v = v;
        }
    }
}
