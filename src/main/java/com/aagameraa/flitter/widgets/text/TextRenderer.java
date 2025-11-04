package com.aagameraa.flitter.widgets.text;

import com.aagameraa.flitter.interfaces.IElementRenderer;
import com.aagameraa.flitter.models.Position;
import com.aagameraa.flitter.models.Size;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TextRenderer implements IElementRenderer {
    private final ResourceLocation valueTexture;
    private final Position position;
    private final Size size;

    public TextRenderer(
            @NotNull Position position,
            @NotNull TextElement element,
            @NotNull TextLayout layout
    ) {
        this.valueTexture = element.valueTexture();
        this.position = position;
        this.size = layout.size();
    }

    @Override
    public void render(@NotNull GuiGraphics graphics) {
        graphics.blit(
                this.valueTexture,
                this.position.leftPos(), this.position.topPos(),
                0, 0,
                this.size.width(), this.size.height(),
                this.size.width(), this.size.height()
        );
    }
}
