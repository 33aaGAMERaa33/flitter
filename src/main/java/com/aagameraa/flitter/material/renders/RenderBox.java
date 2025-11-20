package com.aagameraa.flitter.material.renders;

import com.aagameraa.flitter.material.RenderObject;
import com.aagameraa.flitter.models.Constraints;
import com.aagameraa.flitter.models.Size;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class RenderBox extends RenderObject {
    private @Nullable Size size;

    @Override
    public final void layout(@NotNull Constraints constraints) {
        if(!this.needsLayout()) return;
        this.setConstraints(constraints);
        this.performLayout();
        this.needsLayout = false;
    }

    protected abstract void performLayout();

    protected void setSize(@Nullable Size size) {
        this.size = size;
    }

    protected @NotNull Size getSize() {
        return Objects.requireNonNull(this.size);
    }
}
