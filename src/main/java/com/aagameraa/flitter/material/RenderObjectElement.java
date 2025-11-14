package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class RenderObjectElement extends Element {
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
        if(!(newWidget instanceof RenderObjectWidget renderObjectWidget)) return;
        renderObjectWidget.updateRenderObject(this, Objects.requireNonNull(this.getRenderObject()));
    }

    @Override
    public @NotNull RenderObjectWidget getWidget() {
        return (RenderObjectWidget) super.getWidget();
    }
}
