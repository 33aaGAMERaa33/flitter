package com.aagameraa.flitter.models;

import org.jetbrains.annotations.NotNull;

public class ValueKey {
    private final @NotNull Object value;

    public ValueKey(@NotNull Object value) {
        this.value = value;
    }

    public @NotNull Object key() {
        return value;
    }
}
