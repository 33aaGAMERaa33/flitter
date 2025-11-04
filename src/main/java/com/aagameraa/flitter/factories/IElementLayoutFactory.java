package com.aagameraa.flitter.factories;

import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.interfaces.IElementLayout;
import com.aagameraa.flitter.models.BoxConstraints;
import org.jetbrains.annotations.NotNull;

public interface IElementLayoutFactory<E extends Element, L extends IElementLayout> {
    @NotNull L buildLayout(@NotNull E element, @NotNull BoxConstraints constraints);
}
