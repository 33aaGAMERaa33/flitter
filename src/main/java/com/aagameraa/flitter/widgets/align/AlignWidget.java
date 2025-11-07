package com.aagameraa.flitter.widgets.align;

import com.aagameraa.flitter.material.Widget;
import com.aagameraa.flitter.material.Element;
import org.jetbrains.annotations.NotNull;

public class AlignWidget extends Widget {
    private final @NotNull Alignment alignment;
    private final @NotNull Widget child;

    public AlignWidget(@NotNull Alignment alignment, @NotNull Widget child) {
        this.alignment = alignment;
        this.child = child;
    }

    @Override
    public Element createElement() {
        final var element = new AlignElement(this.alignment, this.child.createElement());
        element.attach(this);

        return element;
    }

    public static class Builder {
        private @NotNull Alignment alignment = Alignment.TOP_START;
        private final @NotNull Widget child;

        public Builder(@NotNull Widget child) {
            this.child = child;
        }

        public Builder alignment(@NotNull Alignment alignment) {
            this.alignment = alignment;
            return this;
        }

        public AlignWidget build() {
            return new AlignWidget(this.alignment, this.child);
        }
    }
}
