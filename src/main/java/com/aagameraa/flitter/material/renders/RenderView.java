package com.aagameraa.flitter.material.renders;

import com.aagameraa.flitter.interfaces.IMultiChildRenderObject;
import com.aagameraa.flitter.material.RenderObject;
import com.aagameraa.flitter.models.Offset;
import com.aagameraa.flitter.models.Size;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class RenderView extends RenderBox implements IMultiChildRenderObject {
    private @Nullable List<@NotNull RenderObject> childRenderObjects;

    @Override
    public void render(@NotNull GuiGraphics graphics, @NotNull Offset offset) {
        if(childRenderObjects == null) return;

        for(final var childRenderObject : this.getChildRenderObjects()) childRenderObject.render(graphics, offset);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void performLayout() {
        this.setSize(new Size(this.getConstraints().maxWidth, this.getConstraints().maxHeight));
        for(final var childRenderObject : this.getChildRenderObjects()) childRenderObject.layout(this.getConstraints());
    }

    @Override
    public boolean needsChildLayout() {
        return false;
    }

    @Override
    public void setChildRenderObjects(@NotNull List<@NotNull RenderObject> childRenderObjects) {
        this.childRenderObjects = childRenderObjects;
    }

    private @NotNull List<@NotNull RenderObject> getChildRenderObjects() {
        return Objects.requireNonNull(childRenderObjects);
    }
}
