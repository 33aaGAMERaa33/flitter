package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Element extends BuildContext {
    private @Nullable Object slot = null;
    private @Nullable Element parent = null;
    private @Nullable RenderObject renderObject = null;

    public Element(@NotNull Widget widget) {
        super(widget);
    }

    public void mount(@Nullable Element parent, @Nullable Object slot) {
        this.slot = slot;
        this.parent = parent;
    }

    public @Nullable Element getParent() {
        return this.parent;
    }

    public @Nullable Object getSlot() {
        return this.slot;
    }

    public void setRenderObject(@NotNull RenderObject renderObject) {
        this.renderObject = renderObject;
    }

    public @Nullable RenderObject getRenderObject() {
        return this.renderObject;
    }
}
