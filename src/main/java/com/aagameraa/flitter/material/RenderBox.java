package com.aagameraa.flitter.material;

import com.aagameraa.flitter.models.Constraints;
import com.aagameraa.flitter.models.Offset;
import com.aagameraa.flitter.models.Size;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class RenderBox extends RenderObject {
    private @Nullable Size size;

    @Override
    public void layout(@NotNull Constraints constraints) {
        if(!this.isNeedsLayout()) return;
        super.layout(constraints);
        this.performLayout();
    }

    protected abstract void performLayout();

    protected void setSize(@Nullable Size size) {
        this.size = size;
    }

    protected @NotNull Size getSize() {
        return Objects.requireNonNull(this.size);
    }
}
