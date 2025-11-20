package com.aagameraa.flitter.material.elements;

import com.aagameraa.flitter.material.Widget;
import com.aagameraa.flitter.material.widgets.StateWidget;
import com.aagameraa.flitter.material.widgets.StatefulWidget;
import org.jetbrains.annotations.NotNull;


public class StatefulElement extends ComponentElement {
    private final @NotNull StateWidget<StatefulWidget> state;

    public StatefulElement(@NotNull StatefulWidget widget) {
        super(widget);
        @SuppressWarnings("unchecked")
        final StateWidget<StatefulWidget> state = (StateWidget<StatefulWidget>) widget.createState();
        state.attach(widget, this);
        this.state = state;
        this.state.initState();
    }

    @Override
    public void unmount() {
        super.unmount();
        this.state.dispose();
    }

    @Override
    public @NotNull Widget build() {
        return this.state.build(this);
    }

    @Override
    public @NotNull StatefulWidget getWidget() {
        return (StatefulWidget) super.getWidget();
    }
}
