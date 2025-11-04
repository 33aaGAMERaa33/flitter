package com.aagameraa.flitter.widgets.column;

import com.aagameraa.flitter.interfaces.IElementRenderer;
import com.aagameraa.flitter.material.BuildRenderer;
import com.aagameraa.flitter.models.Position;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ColumnRenderer implements IElementRenderer {
    private final @NotNull List<IElementRenderer> childRenderers;

    public ColumnRenderer(
            @NotNull Position position,
            @NotNull ColumnElement element,
            @NotNull ColumnLayout layout
    ) {
        this.childRenderers = new ArrayList<>();

        for(final var child : element.children()) {
            final var childLayout = layout.childLayouts().get(child);
            final var translateValue = layout.childTranslateValues().get(child);
            final var translatedPosition = new Position(position.leftPos(), position.topPos() + translateValue);
            final var childRenderer = new BuildRenderer(translatedPosition, child, childLayout);

            this.childRenderers.add(childRenderer);
        }
    }
    @Override
    public void render(@NotNull GuiGraphics graphics) {
        for(final var childRenderer : childRenderers) {
            childRenderer.render(graphics);
        }
    }
}
