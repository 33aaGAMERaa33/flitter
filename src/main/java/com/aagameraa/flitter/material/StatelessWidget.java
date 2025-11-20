package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

public abstract class StatelessWidget extends Widget {
    public abstract @NotNull Widget build(@NotNull BuildContext context);

    @Override
    public @NotNull Element createElement() {
        return new StatelessElement(this);
    }
}
