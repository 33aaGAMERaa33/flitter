package com.aagameraa.flitter.widgets.align;

import com.aagameraa.flitter.material.Element;
import org.jetbrains.annotations.NotNull;

public class AlignElement extends Element {
    private final @NotNull Alignment alignment;
    private final @NotNull Element child;

    public AlignElement(@NotNull Alignment alignment, @NotNull Element child) {
        this.alignment = alignment;
        this.child = child;
        this.child.mount(this);
    }

    public @NotNull Alignment align() {
        return alignment;
    }

    @NotNull Element child() {
        return this.child;
    }
}
