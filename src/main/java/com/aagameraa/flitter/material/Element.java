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

    public void unmount() {
        this.parent = null;
        this.slot = null;
    }

    public void update(@NotNull Widget newWidget) {
        this.setWidget(newWidget);
    }

    public boolean canUpdate(@NotNull Widget newWidget) {
        /*
        System.out.printf(
                "Old: %s, New: %s, Equals: %s\n",
                this.getWidget().getClass().getSimpleName(),
                newWidget.getClass().getSimpleName(),
                this.getWidget().getClass().isInstance(newWidget)
        );
        * */

        return this.getWidget().getClass().isInstance(newWidget);
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
