package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class ComponentElement extends Element {
    private @Nullable Element child = null;

    public ComponentElement(@NotNull Widget widget) {
        super(widget);
    }

    public void setChild(@Nullable Element child) {
        this.child = child;
    }

    public @NotNull Element getChild() {
        return Objects.requireNonNull(child);
    }
}
