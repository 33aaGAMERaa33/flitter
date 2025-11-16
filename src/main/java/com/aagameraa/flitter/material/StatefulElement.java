package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class StatefulElement extends ComponentElement {
    private final @NotNull StateWidget<StatefulWidget> state;

    public StatefulElement(@NotNull StatefulWidget widget) {
        super(widget);
        @SuppressWarnings("unchecked")
        final StateWidget<StatefulWidget> state = (StateWidget<StatefulWidget>) widget.createState();
        state.attach(widget, this);
        this.state = state;
    }

    @Override
    public void mount(@Nullable Element parent, @Nullable Object slot) {
        super.mount(parent, slot);
        this.state.initState();
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
