package com.aagameraa.flitter.material;

import com.aagameraa.flitter.interfaces.IWidget;
import org.jetbrains.annotations.NotNull;

public class StatelessElement extends Element {
    private final @NotNull StatelessWidget widget;

    public StatelessElement(@NotNull StatelessWidget widget) {
        this.widget = widget;
    }

    public @NotNull IWidget build() {
        return widget.build(this);
    }
}
