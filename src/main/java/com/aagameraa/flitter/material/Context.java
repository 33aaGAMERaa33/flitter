package com.aagameraa.flitter.material;

import com.aagameraa.flitter.interfaces.IWidget;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Stack;

public class Context {
    private final Stack<IWidget> widgets;
    private final Stack<Context> contexts;

    public Context() {
        this.widgets = new Stack<>();
        this.contexts = new Stack<>();
    }

    public Context(@NotNull Context context) {
        this();
        widgets.addAll(context.widgets);
        contexts.addAll(context.contexts);
    }

    public @NotNull Context pushNewContext() {
        final var newContext = new Context();
        this.contexts.push(newContext);

        return newContext;
    }

    public <T extends IWidget> void pushWidget(T widget) {
        this.widgets.push(widget);
    }

    public void popWidget() {
        if(this.widgets.empty()) return;
       this.widgets.pop();
    }

    public Stack<Context> getContextsStack() {
        return contexts;
    }

    public Stack<IWidget> getWidgetsStack() {
        return widgets;
    }

    public int countAllWidgets() {
        int total = widgets.size();

        for (Context childContext : contexts) {
            total += childContext.countAllWidgets();
        }

        return total;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Context c)) return false;
        return widgets.equals(c.widgets) && contexts.equals(c.contexts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(widgets.hashCode(), contexts.hashCode());
    }

    @Override
    public String toString() {
        return String.format(
                "Context: \n%s: %d\n%s: %d",
                "WidgetsStack", widgets.size(),
                "ContextsStack", contexts.size()
        );
    }
}
