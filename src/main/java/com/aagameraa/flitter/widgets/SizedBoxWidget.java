package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.material.BuildContext;
import com.aagameraa.flitter.material.RenderObject;
import com.aagameraa.flitter.material.SingleChildRenderObjectWidget;
import com.aagameraa.flitter.material.Widget;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SizedBoxWidget extends SingleChildRenderObjectWidget {
    private final @Nullable Widget child;
    private final @Nullable Integer width;
    private final @Nullable Integer height;

    public SizedBoxWidget(@Nullable Integer width, @Nullable Integer height, @Nullable Widget child) {
        super(child != null ? child : new EmptyWidget());
        this.width = width;
        this.height = height;
        this.child = super.getChild();
    }

    @Override
    public @NotNull RenderObject createRenderObject(@NotNull BuildContext context) {
        return null;
    }

    @Override
    public void updateRenderObject(@NotNull BuildContext context, @NotNull RenderObject newRenderObject) {

    }
}
