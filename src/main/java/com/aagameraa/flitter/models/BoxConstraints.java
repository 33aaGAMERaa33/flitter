package com.aagameraa.flitter.models;

import org.jetbrains.annotations.NotNull;

public record BoxConstraints(int minWidth, int maxWidth, int minHeight, int maxHeight) {
    public @NotNull BoxConstraints tighten(int width, int height) {
        return new BoxConstraints(width, width, height, height);
    }

    public @NotNull BoxConstraints loosen() {
        return new BoxConstraints(0, maxWidth, 0, maxHeight);
    }

    public static @NotNull BoxConstraints infinite() {
        return new BoxConstraints(0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
    }

    public boolean isTight() {
        return minWidth == maxWidth && minHeight == maxHeight;
    }
}
