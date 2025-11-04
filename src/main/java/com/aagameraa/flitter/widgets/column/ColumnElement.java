package com.aagameraa.flitter.widgets.column;

import com.aagameraa.flitter.material.Element;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ColumnElement extends Element {
    private final @NotNull List<Element> children;
    private final int spacing;

    public ColumnElement(@NotNull List<Element> children, int spacing) {
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
