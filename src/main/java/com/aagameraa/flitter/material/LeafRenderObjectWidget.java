package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

public abstract class LeafRenderObjectWidget extends RenderObjectWidget {
    @Override
    public @NotNull Element createElement() {
        return new LeafRenderObjectElement(this);
    }
}
