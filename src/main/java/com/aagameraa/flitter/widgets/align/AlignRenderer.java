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
    private final @NotNull Position position;
    private final @NotNull AlignElement element;
    private final @NotNull AlignLayout layout;

    public AlignRenderer(@NotNull Position position, @NotNull AlignElement element, @NotNull AlignLayout layout) {
        this.element = element;
        this.layout = layout;
        this.position = position;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics) {

    }

    @Override
    public @NotNull IElementRenderer buildChildRenderer(
            @NotNull Element child,
            @NotNull IElementLayout childLayout,
            @NotNull IElementRendererFactory<Element, IElementLayout> childRendererFactory
    ) {
        return childRendererFactory.buildRenderer(
                computeAlignedPosition(this.element.align(), this.layout.constraints(), childLayout.size(), this.position),
                child,
                childLayout
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
