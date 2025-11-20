package com.aagameraa.flitter.material.widgets;

import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.material.Widget;
import com.aagameraa.flitter.material.elements.MultiChildRenderObjectElement;
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
