package com.aagameraa.flitter.exceptions;

import com.aagameraa.flitter.material.Element;
import org.jetbrains.annotations.NotNull;

public class RenderIsNotSingleChildRenderObjectException extends RuntimeException {
    public RenderIsNotSingleChildRenderObjectException(@NotNull Element element) {
        super(String.format("Element render is not ISingleChildRenderObject %s", element.getWidget().getClass().getSimpleName()));
    }
}
