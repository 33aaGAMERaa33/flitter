package com.aagameraa.flitter.material;

import com.aagameraa.flitter.exceptions.IncorrectRenderException;
import com.aagameraa.flitter.exceptions.IncorrectWidgetProvidedException;
import com.aagameraa.flitter.interfaces.IMultiChildRenderObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MultiChildRenderObjectElement extends RenderObjectElement {
    private @NotNull List<@NotNull Element> childrens;

    public MultiChildRenderObjectElement(@NotNull MultiChildRenderObjectWidget widget) {
        super(widget);
        this.childrens = widget.getChildrens().stream().map(Widget::createElement).toList();
    }

    @Override
    public void mount(@Nullable Element parent, @Nullable Object slot) {
        super.mount(parent, slot);
        this.updateChildRenderObjects();
    }

    @Override
    public void update(@NotNull Widget newWidget) {
        super.update(newWidget);

        if(!(newWidget instanceof MultiChildRenderObjectWidget multiChildRenderObjectWidget)) throw new IncorrectWidgetProvidedException(
                newWidget,
                MultiChildRenderObjectWidget.class
        );

        for(int i = 0; i < multiChildRenderObjectWidget.getChildrens().size(); i++) {
            final var newChildWidget = multiChildRenderObjectWidget.getChildrens().get(i);

            if(i < this.childrens.size()) {
                final var oldChild = this.childrens.get(i);
                if(oldChild.canUpdate(newChildWidget)) oldChild.update(newWidget);
                else {
                    this.childrens.remove(i);
                    this.childrens.add(i, newChildWidget.createElement());
                }
            }else {
                final var newChild = newChildWidget.createElement();
                newChild.mount(this, null);
            }
        }

        this.updateChildRenderObjects();
    }

    private void updateChildRenderObjects() {
        assert this.getRenderObject() != null;
        final var childRenderObjects = new ArrayList<@NotNull RenderObject>();

        for(final var child : this.childrens) {
            child.mount(this, null);
            if(child.getRenderObject() == null) continue;

            childRenderObjects.add(child.getRenderObject());
            this.getRenderObject().adoptChild(child.getRenderObject());
        }

        if(!(this.getRenderObject() instanceof IMultiChildRenderObject renderObject))
            throw new IncorrectRenderException(this, this.getRenderObject(), IMultiChildRenderObject.class);

        renderObject.setChildRenderObjects(childRenderObjects);
    }

    @Override
    public @NotNull MultiChildRenderObjectWidget getWidget() {
        return (MultiChildRenderObjectWidget) super.getWidget();
    }
}
