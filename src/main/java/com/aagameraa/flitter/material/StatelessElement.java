package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

public class StatelessElement extends ComponentElement {
    public StatelessElement(@NotNull StatelessWidget widget) {
        super(widget);
    }

    @Override
    public @NotNull Widget build() {
        return this.getWidget().build(this);
    }

    @Override
    public @NotNull StatelessWidget getWidget() {
        return (StatelessWidget) super.getWidget();
    }
}
