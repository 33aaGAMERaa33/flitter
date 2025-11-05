package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class BuildContext {
    private boolean mounted;
    private @Nullable Widget widget;

    public BuildContext() {
        this.mounted = true;
    }

    public void attach(@NotNull Widget widget) {
        this.widget = widget;
    }

    public @NotNull Widget widget() {
        return Objects.requireNonNull(this.widget);
    }

    public void dispose() {
        if(!this.mounted) return;
        this.mounted = false;
        this.widget = null;
    }

    public boolean mounted() {
        return this.mounted;
    }
}
