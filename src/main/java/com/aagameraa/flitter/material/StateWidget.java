package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class StateWidget<T extends StatefulWidget> extends Widget {
    private @Nullable T widget;
    private @Nullable StateElement<T> element;

    public abstract @NotNull Widget build(BuildContext context);

    public void attach(@NotNull T widget) {
        this.widget = widget;
    }

    @Override
    public Element createElement() {
        final var element = new StateElement<>(this);
        this.element = element;

        return element;
    }

    public void initState() {

    }

    public void setState(Runnable update) {
        update.run();
        this.element().markNeedsBuild();
    }

    public @NotNull T widget() {
        return Objects.requireNonNull(this.widget);
    }

    public @NotNull StateElement<T> element() {
        return Objects.requireNonNull(this.element);
    }
}
