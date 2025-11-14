package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class StatelessElement extends ComponentElement {
    public StatelessElement(@NotNull StatelessWidget widget) {
        super(widget);
    }

    @Override
    public void mount(@Nullable Element parent, @Nullable Object slot) {
        super.mount(parent, slot);
        this.buildChild();
    }

    @Override
    public void update(@NotNull Widget newWidget) {
        super.update(newWidget);
        this.buildChild();
    }

    private void buildChild() {
        final var child = this.getWidget().build(this).createElement();
        child.mount(this, null);
        this.setChild(child);
    }

    public @NotNull Element getChild() {
        return Objects.requireNonNull(super.getChild());
    }

    @Override
    public @NotNull StatelessWidget getWidget() {
        return (StatelessWidget) super.getWidget();
    }
}
