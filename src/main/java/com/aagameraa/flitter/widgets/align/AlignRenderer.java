package com.aagameraa.flitter.widgets.align;

import com.aagameraa.flitter.factories.IElementRendererFactory;
import com.aagameraa.flitter.interfaces.ICompoundElementRenderer;
import com.aagameraa.flitter.interfaces.IElementLayout;
import com.aagameraa.flitter.interfaces.IElementRenderer;
import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.models.BoxConstraints;
import com.aagameraa.flitter.models.Position;
import com.aagameraa.flitter.models.Size;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

public class AlignRenderer implements ICompoundElementRenderer {
    private final Alignment alignment;
    private final BoxConstraints constraints;

    public AlignRenderer(@NotNull Position position, @NotNull AlignElement element, @NotNull AlignLayout layout) {
        this.alignment = element.align();
        this.constraints = layout.
    }

    @Override
    public void render(@NotNull GuiGraphics graphics) {

    }

    @Override
    public @NotNull IElementRenderer buildElementRenderer(
            @NotNull Position position,
            @NotNull Element element,
            @NotNull IElementRendererFactory<Element, IElementLayout> rendererFactory
    ) {
        return rendererFactory.buildRenderer(
                computeAlignedPosition(alignment, )
        );
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
