package com.aagameraa.flitter.widgets.row;

import com.aagameraa.flitter.material.Element;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RowElement extends Element {
    private final @NotNull List<Element> children;
    private final int spacing;

    public RowElement(@NotNull List<Element> children, int spacing) {
        this.children = children;
        this.spacing = spacing;

        for (Element child : children) {
            child.mount(this);
        }
    }

    public @NotNull List<Element> children() {
        return children;
    }

    public int spacing() {
        return spacing;
    }
}
