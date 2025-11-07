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

    /**
     * Verifica se este constraint é maior (ou igual) que outro constraint.
     * Isso significa que seus limites mínimos e máximos são todos maiores ou iguais.
     */
    public boolean isLargerThan(@NotNull BoxConstraints other) {
        return this.minWidth >= other.minWidth
                && this.maxWidth >= other.maxWidth
                && this.minHeight >= other.minHeight
                && this.maxHeight >= other.maxHeight;
    }

    public static BoxConstraints byMin(int minWidth, int minHeight) {
        return new BoxConstraints(minWidth, minWidth, minHeight, minHeight);

    }
}
