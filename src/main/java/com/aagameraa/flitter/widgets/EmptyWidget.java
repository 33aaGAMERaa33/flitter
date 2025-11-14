package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.material.EmptyElement;
import com.aagameraa.flitter.material.Widget;
import org.jetbrains.annotations.NotNull;

public class EmptyWidget extends Widget {
    @Override
    public @NotNull Element createElement() {
        return new EmptyElement(this);
    }
}
