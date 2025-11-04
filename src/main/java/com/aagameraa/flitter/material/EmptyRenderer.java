package com.aagameraa.flitter.material;

import com.aagameraa.flitter.interfaces.IElementRenderer;
import com.aagameraa.flitter.models.Position;
import com.aagameraa.flitter.widgets.text.TextElement;
import com.aagameraa.flitter.widgets.text.TextLayout;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

public class EmptyRenderer implements IElementRenderer {
    public EmptyRenderer(
            @NotNull Position position,
            @NotNull TextElement element,
            @NotNull TextLayout layout
    ) {

    }

    @Override
    public void render(@NotNull GuiGraphics graphics) {

    }
}
