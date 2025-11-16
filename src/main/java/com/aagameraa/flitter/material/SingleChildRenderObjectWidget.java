package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class SingleChildRenderObjectWidget extends RenderObjectWidget {
    private final @Nullable Widget child;

    public SingleChildRenderObjectWidget(@Nullable Widget child) {
        this.child = child;
    }

    @Override
    public @NotNull Element createElement() {
        return new SingleChildRenderObjectElement(this);
    }
    public @Nullable Widget getChild() {
        return this.child;
    }
}
