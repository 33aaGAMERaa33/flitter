package com.aagameraa.flitter.factories;

import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.interfaces.IElementLayout;
import com.aagameraa.flitter.interfaces.IElementRenderer;
import com.aagameraa.flitter.models.Position;
import org.jetbrains.annotations.NotNull;

public interface IElementRendererFactory<E extends Element, L extends IElementLayout> {
    IElementRenderer buildRenderer(@NotNull Position position, @NotNull E element, @NotNull L layout);
}
