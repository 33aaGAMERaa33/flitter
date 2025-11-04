package com.aagameraa.flitter.material;

import com.aagameraa.flitter.interfaces.IWidget;
import org.jetbrains.annotations.NotNull;

public abstract class StatefulWidget implements IWidget {
    public abstract @NotNull StateWidget<StatefulWidget> createState();

    @Override
    public Element createElement() {
        return new StatefulElement(this);
    }
}
