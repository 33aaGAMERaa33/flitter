package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.material.Widget;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ColumnWidget extends Widget {
    private final @NotNull List<Widget> childrens;

    public ColumnWidget(@NotNull List<Widget> childrens) {
        this.childrens = childrens;
    }

    @Override
    public @NotNull Element createElement() {
        return null;
    }
}
