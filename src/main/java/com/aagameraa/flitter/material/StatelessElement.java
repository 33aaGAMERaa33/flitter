package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

public class StatelessElement extends Element {
    private final @NotNull StatelessWidget widget;

    public StatelessElement(@NotNull StatelessWidget widget) {
        this.widget = widget;
    }

    public Widget build() {
        return this.widget.build(this);
    }
}
