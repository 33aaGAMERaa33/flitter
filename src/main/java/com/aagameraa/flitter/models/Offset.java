package com.aagameraa.flitter.models;

import org.jetbrains.annotations.NotNull;

public record Offset(int leftPos, int topPos) {
    @Override
    public @NotNull String toString() {
        return String.format("Position(leftPos: %d, topPos: %d)", leftPos, topPos);
    }
}
