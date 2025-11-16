package com.aagameraa.flitter.material;

import com.aagameraa.flitter.FlitterRenderer;
import com.aagameraa.flitter.models.Constraints;
import com.aagameraa.flitter.models.Offset;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class RenderObject {
    private boolean needsLayout = true;
    private @Nullable Constraints constraints;
    private @Nullable RenderObject parent = null;

    public abstract void render(@NotNull GuiGraphics graphics, @NotNull Offset offset);
    public abstract boolean needsChildLayout();

    public void update() {
        this.markNeedsLayout();
    }

    public void layout(@NotNull Constraints constraints) {
        this.needsLayout = false;
        this.setConstraints(constraints);
    }

    public void relayout() {
        if(!this.needsLayout) return;
        this.layout(this.getConstraints());
    }

    public void markNeedsLayout() {
        if(this.needsLayout) return;
        this.needsLayout = true;

        if(this.parent != null && this.parent.needsChildLayout()) this.parent.markNeedsLayout();
        else FlitterRenderer.getInstance().scheduleLayoutFor(this);
    }

    public boolean isNeedsLayout() {
        return needsLayout;
    }

    public void adoptChild(@NotNull RenderObject child) {
        assert child.parent == null;
        child.parent = this;
    }

    public @Nullable RenderObject getParent() {
        return parent;
    }

    protected void setConstraints(@Nullable Constraints constraints) {
        this.constraints = constraints;
    }

    public @NotNull Constraints getConstraints() {
        return Objects.requireNonNull(constraints);
    }
}
