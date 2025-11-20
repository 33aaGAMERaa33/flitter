package com.aagameraa.flitter.material.widgets;

import com.aagameraa.flitter.material.BuildContext;
import com.aagameraa.flitter.material.RenderObject;
import com.aagameraa.flitter.material.Widget;
import org.jetbrains.annotations.NotNull;

public abstract class RenderObjectWidget extends Widget {
    public abstract @NotNull RenderObject createRenderObject(@NotNull BuildContext context);
    public abstract void updateRenderObject(@NotNull RenderObject renderObject);
}
