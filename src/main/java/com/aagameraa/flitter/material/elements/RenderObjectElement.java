package com.aagameraa.flitter.material.elements;

import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.material.Widget;
import com.aagameraa.flitter.material.widgets.RenderObjectWidget;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class RenderObjectElement extends Element {
    public RenderObjectElement(@NotNull RenderObjectWidget widget) {
        super(widget);
    }

    @Override
    public void mount(@Nullable Element parent, @Nullable Object slot) {
        super.mount(parent, slot);
        this.setRenderObject(this.getWidget().createRenderObject(this));
    }

    @Override
    public void update(@NotNull Widget newWidget) {
        super.update(newWidget);
        assert this.getWidget() != newWidget;
        assert this.getRenderObject() != null;
        this.getWidget().updateRenderObject(this.getRenderObject());
        this.getRenderObject().markNeedsLayout();
    }

    @Override
    public @NotNull RenderObjectWidget getWidget() {
        return (RenderObjectWidget) super.getWidget();
    }
}
