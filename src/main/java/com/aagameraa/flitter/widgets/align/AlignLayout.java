package com.aagameraa.flitter.widgets.align;

import com.aagameraa.flitter.factories.IElementLayoutFactory;
import com.aagameraa.flitter.interfaces.ICompoundElementLayout;
import com.aagameraa.flitter.interfaces.IElementLayout;
import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.models.BoxConstraints;
import com.aagameraa.flitter.models.Size;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class AlignLayout implements ICompoundElementLayout {
    private @Nullable Size size;
    private @NotNull BoxConstraints constraints;

    public AlignLayout(@NotNull AlignElement element, @NotNull BoxConstraints constraints) {
        this.constraints = constraints;
    }

    @Override
    public @NotNull Size size() {
        return Objects.requireNonNull(size);
    }

    @Override
    public @NotNull BoxConstraints constraints() {
        return this.constraints;
    }

    @Override
    public @NotNull IElementLayout buildElementLayout(@NotNull Element element, @NotNull IElementLayoutFactory<Element, IElementLayout> elementLayoutFactory) {
        final var layout = elementLayoutFactory.buildLayout(element, constraints);
        this.size = layout.size();

        return layout;
    }
}
