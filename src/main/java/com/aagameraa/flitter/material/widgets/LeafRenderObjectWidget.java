package com.aagameraa.flitter.material.widgets;

import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.material.elements.LeafRenderObjectElement;
import org.jetbrains.annotations.NotNull;

public abstract class LeafRenderObjectWidget extends RenderObjectWidget {
    @Override
    public @NotNull Element createElement() {
        return new LeafRenderObjectElement(this);
    }
}
