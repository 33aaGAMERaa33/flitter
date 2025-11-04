package com.aagameraa.flitter.material;

import com.aagameraa.flitter.interfaces.IWidget;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class BuildContext {
    private @Nullable IWidget widget;

    public BuildContext() {

    }

    public void attach(@NotNull IWidget widget) {
        this.widget = widget;
    }

    public @NotNull IWidget widget() {
        return Objects.requireNonNull(this.widget);
    }
}
