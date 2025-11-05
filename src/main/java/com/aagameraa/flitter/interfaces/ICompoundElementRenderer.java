package com.aagameraa.flitter.interfaces;

import com.aagameraa.flitter.factories.IElementRendererFactory;
import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.models.Position;
import org.jetbrains.annotations.NotNull;

public interface ICompoundElementRenderer extends IElementRenderer {
    @NotNull IElementRenderer buildElementRenderer(
            @NotNull Position position,
            @NotNull Element element,
            @NotNull IElementRendererFactory<Element, IElementLayout> rendererFactory
    );
}
