package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class BuildContext {
    private @NotNull Widget widget;

    public BuildContext(@NotNull Widget widget) {
        this.widget = widget;
    }

    public void setWidget(@NotNull Widget widget) {
        this.widget = widget;
    }

    public @NotNull Widget getWidget() {
        return Objects.requireNonNull(this.widget);
    }
}
