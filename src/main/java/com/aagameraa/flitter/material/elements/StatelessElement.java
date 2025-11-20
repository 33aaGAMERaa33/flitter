package com.aagameraa.flitter.material.elements;

import com.aagameraa.flitter.material.Widget;
import com.aagameraa.flitter.material.widgets.StatelessWidget;
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
