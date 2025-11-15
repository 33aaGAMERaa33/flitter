package com.aagameraa.flitter.exceptions;

import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.material.RenderObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IncorrectRenderException extends RuntimeException {
    public <T> IncorrectRenderException(@NotNull Element element, @Nullable RenderObject folded, @NotNull Class<T> expected) {
        super(String.format(
                "Incorrect element render: Element: %s, RenderObject founded: %s, RenderObject expected: %s",
                element.getWidget().getClass().getSimpleName(),
                folded == null ? "null" : folded.getClass().getSimpleName(),
                expected.getSimpleName()
        ));
    }
}
