package com.aagameraa.flitter.material;

import com.aagameraa.flitter.interfaces.IWidget;
import org.jetbrains.annotations.NotNull;

public abstract class StateWidget<T extends StatefulWidget> implements IWidget {
    private final @NotNull T widget;

    public StateWidget(@NotNull T widget) {
        this.widget = widget;
    }

    abstract IWidget build(BuildContext context);

    public void setState(Runnable update) {
        update.run();
    }
}
