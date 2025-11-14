package com.aagameraa.flitter.material;

import com.aagameraa.flitter.models.Constraints;
import com.aagameraa.flitter.models.Offset;
import com.aagameraa.flitter.models.Size;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.UUID;

public class RenderParagraph extends RenderBox {
    private @NotNull String value;
    private static final int SCALE = 2;
    private @NotNull ParagraphData data;
    private static final HashMap<UUID, ParagraphData> paragraphCache = new HashMap<>();

    public RenderParagraph(@NotNull String value) {
        this.data = buildParagraphData(value);
        this.value = this.data.value();
        this.performLayout();
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, @NotNull Offset offset) {
        graphics.blit(
                data.valueTexture(),
                offset.leftPos(), offset.topPos(),
                0, 0,
                this.getSize().width(), this.getSize().height(),
                this.getSize().width(), this.getSize().height()
        );
    }

    @Override
    public void update() {
        this.data = buildParagraphData(this.value);
    }

    @Override
    public void performLayout() {
        this.setSize(new Size(this.data.fontMetrics().stringWidth(this.data.value()), this.data.fontMetrics().getHeight()));
        this.setConstraints(Constraints.byMin(this.getSize().width(), this.getSize().height()));
    }

    public void setValue(@NotNull String value) {
        this.value = value;
    }

    private static ParagraphData buildParagraphData(@NotNull String value) {
        final var valueHash = value.hashCode();
        final var msb = ((long) valueHash << 32);
        final var lsb = (valueHash & 0xFFFFFFFFL);
        final var uuid = new UUID(msb, lsb);

        if(paragraphCache.containsKey(uuid)) return paragraphCache.get(uuid);

        final var font = new Font("Arial", Font.BOLD, 8);

        BufferedImage dummy = new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D gDummy = dummy.createGraphics();
        gDummy.scale(SCALE, SCALE);
        gDummy.setFont(font);
        FontMetrics fontMetrics = gDummy.getFontMetrics();
        int width = fontMetrics.stringWidth(value) * SCALE;
        int height = fontMetrics.getHeight() * SCALE;
        gDummy.dispose();

        BufferedImage img = new BufferedImage(
                width, height,
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g = img.createGraphics();
        g.scale(SCALE, SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString(value, 0, fontMetrics.getAscent());
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

        final var paragraphData = new ParagraphData(
                value,
                fontMetrics,
                resourceLocation
        );

        paragraphCache.put(uuid, paragraphData);
        return paragraphData;
    }
}
