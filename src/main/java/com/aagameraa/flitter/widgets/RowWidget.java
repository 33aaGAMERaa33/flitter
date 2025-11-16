package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.material.Axis;
import com.aagameraa.flitter.material.Widget;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RowWidget extends FlexWidget {
    public RowWidget(@NotNull List<Widget> childrens, int spacing) {
        super(childrens, Axis.HORIZONTAL, spacing);
    }

    public static class Builder {
        private int spacing = 0;
        private final @NotNull List<Widget> childrens;

        public Builder(@NotNull List<Widget> childrens) {
            this.childrens = childrens;
        }

        public Builder spacing(int spacing) {
            this.spacing = spacing;
            return this;
        }

        public RowWidget build() {
            return new RowWidget(this.childrens, this.spacing);
        }
    }
}
