package com.aagameraa.flitter.material;

import com.aagameraa.flitter.FlitterRenderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ComponentElement extends Element {
    private @Nullable Element child = null;
    private boolean dirty = true;

    public ComponentElement(@NotNull Widget widget) {
        super(widget);
    }

    public abstract @NotNull Widget build();

    @Override
    public void mount(@Nullable Element parent, @Nullable Object slot) {
        super.mount(parent, slot);
        this.setChild(this.build().createElement());

        assert this.getChild() != null;

        this.getChild().mount(this, null);
        this.dirty = false;
    }

    @Override
    public void update(@NotNull Widget newWidget) {
        super.update(newWidget);
        this.setChild(this.updateChild(this.getChild(), this.build()));
    }

    protected @NotNull Element updateChild(@Nullable Element oldChild, @NotNull Widget newChildWidget) {
        if(oldChild != null && oldChild.canUpdate(newChildWidget)) {
            oldChild.update(newChildWidget);
            return oldChild;
        }

        if(oldChild != null) oldChild.unmount();

        final var newChild = newChildWidget.createElement();
        newChild.mount(this, null);
        return newChild;
    }

    public void markNeedsBuild() {
        if(this.dirty) return;
        this.dirty = true;
        FlitterRenderer.getInstance().scheduleBuildFor(this);
    }

    public void rebuild() {
        if(!this.dirty) return;
        this.dirty = false;
        this.update(this.getWidget());
    }

    public void setChild(@Nullable Element child) {
        this.child = child;
    }

    public @Nullable Element getChild() {
        return child;
    }

    @Override
    public final @Nullable RenderObject getRenderObject() {
        if(this.getChild() == null) return null;
        return this.getChild().getRenderObject();
    }
}
