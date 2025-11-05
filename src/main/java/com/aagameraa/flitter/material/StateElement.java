package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

public class StateElement<T extends StatefulWidget> extends Element {
    private boolean needsBuild = false;
    private final @NotNull StateWidget<T> state;

    public StateElement(@NotNull StateWidget<T> state) {
        this.state = state;
    }

    public void markNeedsBuild() {
        this.needsBuild = true;
    }

    public boolean isNeedsBuild() {
        return needsBuild;
    }

    public @NotNull StateWidget<T> state() {
        return this.state;
    }

    public @NotNull Widget build() {
        this.needsBuild = false;
        return this.state.build(this);
    }
}
