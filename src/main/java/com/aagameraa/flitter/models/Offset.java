package com.aagameraa.flitter.models;

import org.jetbrains.annotations.NotNull;

public record Offset(int leftPos, int topPos) {
    public Offset add(int leftPos, int topPos) {
        return new Offset(this.leftPos + leftPos, this.topPos + topPos);
    }

    @Override
    public @NotNull String toString() {
        return String.format("Offset(leftPos: %d, topPos: %d)", leftPos, topPos);
    }
}
