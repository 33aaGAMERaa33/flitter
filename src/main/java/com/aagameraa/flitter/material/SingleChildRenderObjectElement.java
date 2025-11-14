package com.aagameraa.flitter.material;

import com.aagameraa.flitter.exceptions.RenderIsNotSingleChildRenderObjectException;
import com.aagameraa.flitter.interfaces.ISingleChildRenderObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SingleChildRenderObjectElement extends RenderObjectElement {
    private @Nullable Element child;

    public SingleChildRenderObjectElement(@NotNull SingleChildRenderObjectWidget widget) {
        super(widget);
    }

    @Override
    public void mount(@Nullable Element parent, @Nullable Object slot) {
        super.mount(parent, slot);
        this.child = this.getWidget().getChild().createElement();
        this.child.mount(this, null);
        if(!(this.getRenderObject() instanceof ISingleChildRenderObject renderObject)) throw new RenderIsNotSingleChildRenderObjectException(this);
        renderObject.setChildRenderObject(Objects.requireNonNull(this.getChild().getRenderObject()));
    }

    @Override
    public void update(@NotNull Widget newWidget) {
        super.update(newWidget);
        this.child = this.getWidget().getChild().createElement();
    }

    @Override
    public @NotNull SingleChildRenderObjectWidget getWidget() {
        return (SingleChildRenderObjectWidget) super.getWidget();
    }

    public @NotNull Element getChild() {
        return Objects.requireNonNull(this.child);
    }
}