package com.aagameraa.flitter.material.elements;

import com.aagameraa.flitter.exceptions.IncorrectRenderException;
import com.aagameraa.flitter.exceptions.IncorrectWidgetProvidedException;
import com.aagameraa.flitter.interfaces.ISingleChildRenderObject;
import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.material.Widget;
import com.aagameraa.flitter.material.widgets.SingleChildRenderObjectWidget;
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
        final var widgetChild = this.getWidget().getChild();
        if(widgetChild == null) return;

        this.child = this.getWidget().getChild().createElement();
        this.child.mount(this, null);

        if(!(this.getRenderObject() instanceof ISingleChildRenderObject renderObject)) throw new IncorrectRenderException(
                this, this.getRenderObject(), ISingleChildRenderObject.class
        );

        renderObject.setChildRenderObject(Objects.requireNonNull(this.getChild().getRenderObject()));
        this.getRenderObject().adoptChild(this.getChild().getRenderObject());
    }

    @Override
    public void update(@NotNull Widget newWidget) {
        super.update(newWidget);

        if(!(newWidget instanceof SingleChildRenderObjectWidget singleChildRenderObjectWidget)) throw new IncorrectWidgetProvidedException(
                newWidget,
                SingleChildRenderObjectWidget.class
        );

        final var newChildWidget = singleChildRenderObjectWidget.getChild();
        if(newChildWidget == null) return;

        if(this.getChild().canUpdate(newChildWidget)) {
            this.getChild().update(newChildWidget);
        } else {
            final var newChild = newChildWidget.createElement();
            newChild.mount(this, null);
            this.getChild().unmount();

            this.setChild(newChild);

            if(!(this.getRenderObject() instanceof ISingleChildRenderObject singleChildRenderObject)) throw new IncorrectRenderException(
                    this, this.getRenderObject(), ISingleChildRenderObject.class
            );

            this.getRenderObject().adoptChild(Objects.requireNonNull(this.getChild().getRenderObject()));
            singleChildRenderObject.setChildRenderObject(Objects.requireNonNull(this.getChild().getRenderObject()));
        }
    }

    @Override
    public @NotNull SingleChildRenderObjectWidget getWidget() {
        return (SingleChildRenderObjectWidget) super.getWidget();
    }

    public void setChild(@NotNull Element child) {
        this.child = child;
    }

    public @NotNull Element getChild() {
        return Objects.requireNonNull(this.child);
    }
}