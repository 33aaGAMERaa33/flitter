package com.aagameraa.flitter.material;

import com.aagameraa.flitter.FlitterRenderer;
import com.aagameraa.flitter.interfaces.IElementLayout;
import com.aagameraa.flitter.models.BoxConstraints;
import com.aagameraa.flitter.models.Size;
import org.jetbrains.annotations.NotNull;

public class AdaptativeLayout implements IElementLayout {
    private final @NotNull Size size;
    private final @NotNull Element adaptedElement;
    private final @NotNull BoxConstraints constraints;
    private final @NotNull IElementLayout adaptedElementLayout;

    public AdaptativeLayout(@NotNull Element adaptedElement, @NotNull BoxConstraints constraints) {
        final var elementLayoutFactory = FlitterRenderer.getInstance().getElementLayoutFactory(adaptedElement);
        final var elementLayout = elementLayoutFactory.buildLayout(adaptedElement, constraints);

        this.adaptedElement = adaptedElement;
        this.size = elementLayout.size();
        this.constraints = constraints;
        this.adaptedElementLayout = elementLayout;
    }

    @Override
    public @NotNull Size size() {
        return this.size;
    }

    public @NotNull Element adaptedElement() {
        return adaptedElement;
    }

    public @NotNull BoxConstraints constraints() {
        return constraints;
    }

    public @NotNull IElementLayout adaptedElementLayout() {
        return adaptedElementLayout;
    }
}
