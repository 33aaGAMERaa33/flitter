package com.aagameraa.flitter.material;

import com.aagameraa.flitter.interfaces.IWidget;
import org.jetbrains.annotations.NotNull;


public class StatefulElement extends Element {
    private final @NotNull StatefulWidget widget;

    public StatefulElement(@NotNull StatefulWidget widget) {
        this.widget = widget;
    }

    public @NotNull IWidget build() {
        return this.widget.createState().build(this);
    }

    public @NotNull StatefulWidget widget() {
        return this.widget;
    }
}
