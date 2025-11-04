package com.aagameraa.flitter.material;

import com.aagameraa.flitter.interfaces.IWidget;
import org.jetbrains.annotations.NotNull;

public abstract class StatelessWidget implements IWidget {
    public abstract @NotNull IWidget build(BuildContext context);

    @Override
    public Element createElement() {
        return new StatelessElement(this);
    }
}
