package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

public class StatefulElement extends Element {
    private final @NotNull StateWidget<StatefulWidget> state;

    public <T extends StatefulWidget> StatefulElement(@NotNull T widget) {
        @SuppressWarnings("unchecked")
        final var state = (StateWidget<StatefulWidget>) widget.createState();
        state.attach(widget);
        this.state = state;
    }

    public Widget build() {
        final var element = this.state.createElement();
        element.attach(state);

        return this.state.build(element);
    }
}
