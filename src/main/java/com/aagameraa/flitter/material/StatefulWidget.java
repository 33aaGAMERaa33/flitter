package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

public abstract class StatefulWidget extends Widget {
    public abstract @NotNull StateWidget<? extends StatefulWidget> createState();

    @Override
    public @NotNull Element createElement() {
        return new StatefulElement(this);
    }
}
