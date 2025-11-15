package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class MultiChildRenderObjectWidget extends RenderObjectWidget {
    private final @NotNull List<Widget> childrens;

    public MultiChildRenderObjectWidget(@NotNull List<Widget> childrens) {
        this.childrens = childrens;
    }

    @Override
    public @NotNull Element createElement() {
        return new MultiChildRenderObjectElement(this);
    }

    public @NotNull List<Widget> getChildrens() {
        return childrens;
    }
}
