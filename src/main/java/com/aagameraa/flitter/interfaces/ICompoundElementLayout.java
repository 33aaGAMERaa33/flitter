package com.aagameraa.flitter.interfaces;

import com.aagameraa.flitter.factories.IElementLayoutFactory;
import com.aagameraa.flitter.material.Element;
import org.jetbrains.annotations.NotNull;

public interface ICompoundElementLayout extends IElementLayout {
    @NotNull IElementLayout buildElementLayout(@NotNull Element element, @NotNull IElementLayoutFactory<Element, IElementLayout> elementLayoutFactory);
}
