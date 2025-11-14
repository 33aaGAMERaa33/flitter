package com.aagameraa.flitter.material;

import com.aagameraa.flitter.models.Constraints;
import com.aagameraa.flitter.models.Offset;
import com.aagameraa.flitter.models.Size;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class RenderBox extends RenderObject {
    private @Nullable Size size;
    private @Nullable Constraints constraints;

    @Override
    public void layout(@NotNull Constraints constraints) {
        this.constraints = constraints;
        this.performLayout();
    }

    public abstract void performLayout();

    public void setConstraints(@Nullable Constraints constraints) {
        this.constraints = constraints;
    }

    public @NotNull Constraints getConstraints() {
        return Objects.requireNonNull(this.constraints);
    }
    public void setSize(@Nullable Size size) {
        this.size = size;
    }

    public @NotNull Size getSize() {
        return Objects.requireNonNull(this.size);
    }
}
