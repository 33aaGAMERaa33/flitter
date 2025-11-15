package com.aagameraa.flitter.material;

import com.aagameraa.flitter.exceptions.IncorrectRenderException;
import com.aagameraa.flitter.interfaces.IMultiChildRenderObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MultiChildRenderObjectElement extends RenderObjectElement {
    private final @NotNull List<@NotNull Element> childrens;

    public MultiChildRenderObjectElement(@NotNull MultiChildRenderObjectWidget widget) {
        super(widget);
        this.childrens = widget.getChildrens().stream().map(Widget::createElement).toList();
    }

    @Override
    public void mount(@Nullable Element parent, @Nullable Object slot) {
        super.mount(parent, slot);

        final var childRenderObjects = new ArrayList<@NotNull RenderObject>();

        for(final var child : this.childrens) {
            child.mount(this, null);
            if(child.getRenderObject() == null) continue;

            childRenderObjects.add(child.getRenderObject());
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
