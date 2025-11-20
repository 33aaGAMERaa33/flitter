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
        this.childrens = new ArrayList<>();
    }

    @Override
    public void mount(@Nullable Element parent, @Nullable Object slot) {
        super.mount(parent, slot);

        for(final var childWidget : this.getWidget().getChildrens()) {
            final var child = childWidget.createElement();
            child.mount(this, null);

            this.childrens.add(child);
        }

        this.updateChildRenderObjects();
    }

    @Override
    public void update(@NotNull Widget newWidget) {
        super.update(newWidget);

        if(!(newWidget instanceof MultiChildRenderObjectWidget)) throw new IncorrectWidgetProvidedException(
                newWidget,
                MultiChildRenderObjectWidget.class
        );

        this.updateChildrens(this.getWidget().getChildrens());
        this.updateChildRenderObjects();
    }

    private void updateChildrens(@NotNull List<Widget> childrens) {
        final var newChildrens = new ArrayList<Element>();

        for(int i = 0; i < this.childrens.size(); i++) {
            if(i >= childrens.size()) break;
            final var oldChild = this.childrens.get(i);
            final var newChildWidget = childrens.get(i);

            if(oldChild.canUpdate(newChildWidget)) {
                newChildrens.add(oldChild);
                oldChild.update(newChildWidget);
            }else {
                final var newChild = newChildWidget.createElement();
                newChild.mount(this, null);
                newChildrens.add(newChild);
            }
        }

        this.childrens = newChildrens;
    }

    private void updateChildRenderObjects() {
        assert this.getRenderObject() != null;
        final var childRenderObjects = new ArrayList<@NotNull RenderObject>();

        for(final var child : this.childrens) {
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
