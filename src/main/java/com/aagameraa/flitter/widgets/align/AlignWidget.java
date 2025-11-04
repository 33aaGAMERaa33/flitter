package com.aagameraa.flitter.widgets.align;

import com.aagameraa.flitter.interfaces.IWidget;
import com.aagameraa.flitter.material.Element;
import org.jetbrains.annotations.NotNull;

public class AlignWidget implements IWidget {
    private final @NotNull Alignment alignment;
    private final @NotNull IWidget child;

    public AlignWidget(@NotNull Alignment alignment, @NotNull IWidget child) {
        this.alignment = alignment;
        this.child = child;
    }

    @Override
    public Element createElement() {
        return new AlignElement(this.alignment, this.child.createElement());
    }

    public static class Builder {
        private @NotNull Alignment alignment = Alignment.TOP_START;
        private final @NotNull IWidget child;

        public Builder(@NotNull IWidget child) {
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
