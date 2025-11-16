package com.aagameraa.flitter.exceptions;

import com.aagameraa.flitter.material.RenderObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChildWidgetRenderNotSupportedException extends RuntimeException {
    public ChildWidgetRenderNotSupportedException(@Nullable RenderObject renderObject, @NotNull Class<?> expected) {
        super(String.format(
                "Child widget render not supported: Founded: %s, Expected: %s",
                renderObject == null ? "null" : renderObject.getClass().getSimpleName(),
                expected.getSimpleName()
        ));
    }
}
