package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.material.*;
import com.aagameraa.flitter.material.SingleChildRenderObjectWidget;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AlignWidget extends SingleChildRenderObjectWidget {
    private final @NotNull Alignment alignment;

    public AlignWidget(@NotNull Alignment alignment, @Nullable Widget child) {
        super(child);
        this.alignment = alignment;
    }

    @NotNull
    @Override
    public RenderObject createRenderObject(@NotNull BuildContext context) {
        return new RenderAlign(this.alignment);
    }

    @Override
    public void updateRenderObject(@NotNull RenderObject renderObject) {
        if(!(renderObject instanceof RenderAlign renderAlign)) return;
        renderAlign.setAlignment(this.alignment);
        renderAlign.update();
    }

    public @NotNull Alignment getAlignment() {
        return alignment;
    }
}
