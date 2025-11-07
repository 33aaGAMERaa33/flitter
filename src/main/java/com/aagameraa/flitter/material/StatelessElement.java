package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

public class StatelessElement extends Element {
    private final @NotNull StatelessWidget widget;

    public StatelessElement(@NotNull StatelessWidget widget) {
        this.widget = widget;
    }

    public @NotNull Widget build() {
        return this.widget.build(this);
    }

    @Override
    public @NotNull StatelessWidget widget() {
        return this.widget;
    }
}
