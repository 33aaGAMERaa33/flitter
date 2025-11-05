package com.aagameraa.flitter.widgets.column;

import com.aagameraa.flitter.material.Widget;
import com.aagameraa.flitter.material.Element;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ColumnWidget extends Widget {
    private final @NotNull List<Widget> children;
    private final int spacing;

    public ColumnWidget(@NotNull List<Widget> children, int spacing) {
        this.children = children;
        this.spacing = spacing;
    }

    @Override
    public Element createElement() {
        return new ColumnElement(children.stream()
                .map(Widget::createElement)
                .toList(),
                spacing
        );
    }

    public static class Builder {
        private final @NotNull List<Widget> children;
        private int spacing = 0;

        public Builder(@NotNull List<Widget> children) {
            this.children = children;
        }

        public Builder spacing(int spacing) {
            this.spacing = spacing;
            return this;
        }

        public ColumnWidget build() {
            return new ColumnWidget(children, spacing);
        }
    }
}
