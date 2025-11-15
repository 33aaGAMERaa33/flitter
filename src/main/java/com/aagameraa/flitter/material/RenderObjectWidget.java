package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

public abstract class RenderObjectWidget extends Widget {
    @Override
    public @NotNull Element createElement() {
        return new RenderObjectElement(this);
    }
    public abstract @NotNull RenderObject createRenderObject(@NotNull BuildContext context);
}
