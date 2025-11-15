package com.aagameraa.flitter.models;

import org.jetbrains.annotations.NotNull;

public class Constraints {
    public final int minWidth;
    public final int maxWidth;
    public final int minHeight;
    public final int maxHeight;

    public Constraints(int width, int height) {
        this.minWidth = width;
        this.maxWidth = width;
        this.minHeight = height;
        this.maxHeight = height;
    }

    public Constraints(int minWidth, int maxWidth, int minHeight, int maxHeight) {
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public @NotNull Constraints tighten(int width, int height) {
        return new Constraints(width, width, height, height);
    }

    public @NotNull Constraints loosen() {
        return new Constraints(0, maxWidth, 0, maxHeight);
    }

    public static @NotNull Constraints infinite() {
        return new Constraints(0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
    }

    public boolean isTight() {
        return minWidth == maxWidth && minHeight == maxHeight;
    }

    /**
     * Verifica se este constraint é maior (ou igual) que outro constraint.
     * Isso significa que seus limites mínimos e máximos são todos maiores ou iguais.
     */
    public boolean isLargerThan(@NotNull Constraints other) {
        return this.minWidth >= other.minWidth
                && this.maxWidth >= other.maxWidth
                && this.minHeight >= other.minHeight
                && this.maxHeight >= other.maxHeight;
    }

    public static Constraints byMin(int minWidth, int minHeight) {
        return new Constraints(minWidth, minWidth, minHeight, minHeight);

    }
}
