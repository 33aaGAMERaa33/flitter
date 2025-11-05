package com.aagameraa.flitter.widgets.align;

import com.aagameraa.flitter.material.CompoundElement;
import com.aagameraa.flitter.material.Element;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AlignElement extends CompoundElement {
    private final @NotNull Alignment alignment;
    private final @NotNull Element child;

    public AlignElement(@NotNull Alignment alignment, @NotNull Element child) {
        this.alignment = alignment;
        this.child = child;
    }

    public @NotNull Alignment align() {
        return alignment;
    }

    @Override
    public @NotNull List<Element> elementsToAttach() {
        return List.of(child);
    }
}
