package com.aagameraa.flitter.widgets.align;

import com.aagameraa.flitter.FlitterRenderer;
import com.aagameraa.flitter.interfaces.IElementRenderer;
import com.aagameraa.flitter.models.BoxConstraints;
import com.aagameraa.flitter.models.Position;
import com.aagameraa.flitter.models.Size;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

public class AlignRenderer implements IElementRenderer {
    private final @NotNull IElementRenderer childRenderer;

    public AlignRenderer(@NotNull Position position, @NotNull AlignElement element, @NotNull AlignLayout layout) {
        final var childRendererFactory = FlitterRenderer.getInstance().getElementRendererFactory(element.child());
        final var constraints = layout.constraints();

        final var alignedPosition = computeAlignedPosition(
                element.align(), constraints,
                layout.size(), position
        );

        this.childRenderer = childRendererFactory.buildRenderer(alignedPosition, element.child(), layout.adaptedElementLayout());
    }

    @Override
    public void render(@NotNull GuiGraphics graphics) {
        childRenderer.render(graphics);
    }

    private static Position computeAlignedPosition(
            @NotNull Alignment align,
            @NotNull BoxConstraints constraints,
            @NotNull Size childSize,
            @NotNull Position base
    ) {
        int topPos;
        int leftPos;

        topPos = switch(align.verticalAlignment()) {
            case CENTER -> constraints.maxHeight() / 2 - childSize.height() / 2;
            case BOTTOM -> constraints.maxHeight() - childSize.height();
            default -> 0;
        };

        leftPos = switch (align.horizontalAlignment()) {
            case CENTER -> constraints.maxWidth() / 2 - childSize.width() / 2;
            case END -> constraints.maxWidth() - childSize.width();
            default -> 0;
        };

        return new Position(leftPos + base.leftPos(), topPos + base.topPos());
    }

}
