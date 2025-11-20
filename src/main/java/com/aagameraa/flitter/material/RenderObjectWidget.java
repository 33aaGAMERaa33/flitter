package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

public abstract class RenderObjectWidget extends Widget {
    public abstract @NotNull RenderObject createRenderObject(@NotNull BuildContext context);
    public abstract void updateRenderObject(@NotNull RenderObject renderObject);
}
