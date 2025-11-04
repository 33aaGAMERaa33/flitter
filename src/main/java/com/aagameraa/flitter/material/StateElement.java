package com.aagameraa.flitter.material;

import com.aagameraa.flitter.interfaces.IWidget;
import org.jetbrains.annotations.NotNull;

public class StateElement<T extends StatefulWidget> extends Element {
    private final @NotNull T widget;
    private boolean needsBuild = false;
    private final @NotNull StateWidget<T> state;


    public void markNeedsBuild(@NotNull T widget) {
        this.widget =
        this.needsBuild = true;
    }

    public @NotNull IWidget
}
