package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.material.*;
import org.jetbrains.annotations.NotNull;

public class PaddingWidget extends SingleChildRenderObjectWidget {
    public final @NotNull Widget child;
    public final @NotNull Padding padding;

    public PaddingWidget(@NotNull Padding padding, @NotNull Widget child) {
        super(child);
        this.child = child;
        this.padding = padding;
    }

    @Override
    public @NotNull RenderObject createRenderObject(@NotNull BuildContext context) {
        return new RenderPadding(this.padding);
    }

    @Override
    public void updateRenderObject(@NotNull RenderObject renderObject) {
        assert renderObject instanceof RenderPadding;
        ((RenderPadding) renderObject).setPadding(this.padding);
    }
}
