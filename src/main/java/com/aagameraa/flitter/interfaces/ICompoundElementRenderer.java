package com.aagameraa.flitter.interfaces;

import com.aagameraa.flitter.factories.IElementRendererFactory;
import com.aagameraa.flitter.material.Element;
import org.jetbrains.annotations.NotNull;

public interface ICompoundElementRenderer extends IElementRenderer {
    @NotNull IElementRenderer buildChildRenderer(
            @NotNull Element child,
            @NotNull IElementLayout childLayout,
            @NotNull IElementRendererFactory<Element, IElementLayout> childRendererFactory
    );
}
