package com.aagameraa.flitter.widgets.align;

import com.aagameraa.flitter.factories.IElementLayoutFactory;
import com.aagameraa.flitter.interfaces.ICompoundElementLayout;
import com.aagameraa.flitter.interfaces.IElementLayout;
import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.models.BoxConstraints;
import com.aagameraa.flitter.models.Size;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AlignLayout implements ICompoundElementLayout {
    private @NotNull Size size = new Size(0, 0);
    private final @NotNull BoxConstraints constraints;

    public AlignLayout(@NotNull AlignElement element, @NotNull BoxConstraints constraints) {
        this.constraints = constraints;
    }

    @Override
    public @NotNull Size size() {
        return Objects.requireNonNull(this.size);
    }

    @Override
    public @NotNull BoxConstraints constraints() {
        return this.constraints;
    }

    @Override
    public @NotNull IElementLayout buildChildLayout(@NotNull Element child, IElementLayoutFactory<Element, IElementLayout> childLayoutFactory) {
        final var childLayout = childLayoutFactory.buildLayout(child, constraints);
        if(childLayout.size().isBiggerThan(this.size)) this.size = childLayout.size();

        return childLayout;
    }
}
