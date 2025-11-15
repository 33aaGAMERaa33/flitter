package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class ComponentElement extends Element {
    private @Nullable Element child = null;

    public ComponentElement(@NotNull Widget widget) {
        super(widget);
    }

    public abstract @NotNull Widget build();

    @Override
    public void mount(@Nullable Element parent, @Nullable Object slot) {
        super.mount(parent, slot);
        this.setChild(this.build().createElement());
        this.getChild().mount(this, null);
    }

    public void setChild(@Nullable Element child) {
        this.child = child;
    }

    public @NotNull Element getChild() {
        return Objects.requireNonNull(child);
    }

    @Override
    public @Nullable RenderObject getRenderObject() {
        return this.getChild().getRenderObject();
    }
}
