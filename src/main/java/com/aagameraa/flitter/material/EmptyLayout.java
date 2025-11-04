package com.aagameraa.flitter.material;

import com.aagameraa.flitter.interfaces.IElementLayout;
import com.aagameraa.flitter.models.BoxConstraints;
import com.aagameraa.flitter.models.Size;
import org.jetbrains.annotations.NotNull;

public class EmptyLayout implements IElementLayout {
    private final Size size = new Size(0, 0);

    public EmptyLayout(@NotNull Element element, @NotNull BoxConstraints constraints) {

    }

    @Override
    public @NotNull Size size() {
        return this.size;
    }
}
