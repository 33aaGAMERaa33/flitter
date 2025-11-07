package com.aagameraa.flitter.widgets.text;

import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.material.Widget;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.UUID;

public class TextWidget extends Widget {
    private final @NotNull String value;
    private final @NotNull TextStyle style;
    private static final int SCALE = 2;
    private static final HashMap<UUID, TextElementBuildData> textCache = new HashMap<>();

    public TextWidget(@NotNull String value, @NotNull TextStyle style) {
        this.value = value;
        this.style = style;
    }

    public @NotNull String value() {
        return this.value;
    }

    public @NotNull TextStyle style() {
        return style;
    }

    @Override
    public Element createElement() {
        final var valueHash = value().hashCode();
        final var styleHash = style().hashCode();
        final var msb = ((long) valueHash << 32) | (styleHash & 0xFFFFFFFFL);
        final var lsb = ((long) styleHash << 32) | (valueHash & 0xFFFFFFFFL);
        final var uuid = new UUID(msb, lsb);

        if(textCache.containsKey(uuid)) {
            final var textBuildData = textCache.get(uuid);
            return new TextElement(textBuildData.value(), textBuildData.fontMetrics(), textBuildData.valueTexture());
        }

        BufferedImage dummy = new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D gDummy = dummy.createGraphics();
        gDummy.scale(SCALE, SCALE);
        gDummy.setFont(style().font());
        FontMetrics fm = gDummy.getFontMetrics();
        int width = fm.stringWidth(value()) * SCALE;
        int height = fm.getHeight() * SCALE;
        gDummy.dispose();

        BufferedImage img = new BufferedImage(
                width, height,
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g = img.createGraphics();
        g.scale(SCALE, SCALE);
        g.setFont(style().font());
        g.setColor(style().color());
        g.drawString(value(), 0, fm.getAscent());
        g.dispose();

        // Converte BufferedImage em NativeImage
        NativeImage nativeImage = new NativeImage(img.getWidth(), img.getHeight(), true);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                nativeImage.setPixelRGBA(x, y, img.getRGB(x, y));
            }
        }

        // Cria textura dinâmica e registra
        DynamicTexture texture = new DynamicTexture(nativeImage);
        ResourceLocation resourceLocation = Minecraft.getInstance()
                .getTextureManager()
                .register(uuid.toString(), texture);

        TextElement textElement = new TextElement(value, fm, resourceLocation);
        textCache.put(uuid, new TextElementBuildData(value, fm, resourceLocation));
        textElement.attach(this);

        return textElement;
    }

    public static class Builder {
        private @NotNull TextStyle style;
        private final @NotNull String value;

        public Builder(@NotNull String value) {
            this.value = value.trim();
            this.style = new TextStyle.Builder().build();
        }

        public Builder style(@NotNull TextStyle style) {
            this.style = style;
            return this;
        }

        public TextWidget build() {
            return new TextWidget(this.value, this.style);
        }
    }
}
