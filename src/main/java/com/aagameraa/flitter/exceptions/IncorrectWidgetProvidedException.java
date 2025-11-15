package com.aagameraa.flitter.exceptions;

import com.aagameraa.flitter.material.Widget;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IncorrectWidgetProvidedException extends RuntimeException {
    public IncorrectWidgetProvidedException(@Nullable Widget provided, @NotNull Class<? extends Widget> expected) {
        super(String.format(
                "Incorrect widget provided: Provided: %s, Expected: %s",
                provided == null ? "null" : provided.getClass().getSimpleName(),
                expected.getSimpleName()
        ));
    }
}
