package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

public abstract class StatelessWidget extends Widget {
    public abstract @NotNull Widget build(BuildContext context);

    @Override
    public StatelessElement createElement() {
        return new StatelessElement(this);
    }
}
