package com.aagameraa.flitter.material.widgets;

import com.aagameraa.flitter.material.BuildContext;
import com.aagameraa.flitter.material.elements.StatefulElement;
import com.aagameraa.flitter.material.Widget;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class StateWidget<T extends StatefulWidget> {
    private @Nullable T widget;
    private @Nullable StatefulElement element;

    public abstract @NotNull Widget build(@NotNull BuildContext context);

    public void setState(Runnable update) {
        update.run();
        this.getElement().markNeedsBuild();
    }

    public void attach(@NotNull T widget, @NotNull StatefulElement element) {
        this.widget = widget;
        this.element = element;
    }

    public void initState() {

    }

    public void dispose() {

    }

    public @NotNull T getWidget() {
        return Objects.requireNonNull(this.widget);
    }

    public @NotNull StatefulElement getElement() {
        return Objects.requireNonNull(this.element);
    }
}
