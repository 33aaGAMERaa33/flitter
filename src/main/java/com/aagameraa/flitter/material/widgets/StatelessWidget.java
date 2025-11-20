package com.aagameraa.flitter.material.widgets;

import com.aagameraa.flitter.material.BuildContext;
import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.material.elements.StatelessElement;
import com.aagameraa.flitter.material.Widget;
import org.jetbrains.annotations.NotNull;

public abstract class StatelessWidget extends Widget {
    public abstract @NotNull Widget build(@NotNull BuildContext context);

    @Override
    public @NotNull Element createElement() {
        return new StatelessElement(this);
    }
}
