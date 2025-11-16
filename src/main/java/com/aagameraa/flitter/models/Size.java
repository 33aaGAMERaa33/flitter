package com.aagameraa.flitter.models;

import org.jetbrains.annotations.NotNull;

public record Size(int width, int height) {
    public boolean isBiggerThan(@NotNull Size size)  {
        return this.width > size.width && this.height > size.height;
    }

    @Override
    public @NotNull String toString() {
        return String.format("Size(width: %s, height: %s)", width, height);
    }
}
