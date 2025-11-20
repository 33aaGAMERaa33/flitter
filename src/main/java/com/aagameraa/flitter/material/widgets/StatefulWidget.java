package com.aagameraa.flitter.material.widgets;

import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.material.elements.StatefulElement;
import com.aagameraa.flitter.material.Widget;
import org.jetbrains.annotations.NotNull;

public abstract class StatefulWidget extends Widget {
    public abstract @NotNull StateWidget<? extends StatefulWidget> createState();

    @Override
    public @NotNull Element createElement() {
        return new StatefulElement(this);
    }
}
