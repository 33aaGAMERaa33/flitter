package com.aagameraa.flitter.models;

import org.jetbrains.annotations.NotNull;

public record Size(int width, int height) {
    public boolean isBiggerThan(@NotNull Size size)  {
        return this.width > size.width && this.height > size.height;
    }
}
