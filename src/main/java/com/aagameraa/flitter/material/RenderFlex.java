package com.aagameraa.flitter.material;

import com.aagameraa.flitter.exceptions.ChildWidgetRenderNotSupportedException;
import com.aagameraa.flitter.interfaces.IMultiChildRenderObject;
import com.aagameraa.flitter.models.Offset;
import com.aagameraa.flitter.models.Size;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RenderFlex extends RenderBox implements IMultiChildRenderObject {
    private int spacing;
    private @NotNull Axis direction;
    private @Nullable List<@NotNull RenderObject> childRenderObjects;

    public RenderFlex(
            @NotNull Axis direction,
            int spacing
    ) {
        this.direction = direction;
        this.spacing = spacing;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, @NotNull Offset offset) {
        int currentX = offset.leftPos();
        int currentY = offset.topPos();

        if(this.childRenderObjects == null) return;

        int i = 0;

        for(final var childRenderObject : this.childRenderObjects) {
            if(!(childRenderObject instanceof RenderBox renderBox))
                throw new ChildWidgetRenderNotSupportedException(childRenderObject, RenderBox.class);

            renderBox.render(graphics, new Offset(currentX, currentY));

            i++;

            if(this.direction == Axis.HORIZONTAL) currentX += renderBox.getSize().width() + (i * spacing);
            else currentY += renderBox.getSize().height() + (i * spacing);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void performLayout() {
        if(childRenderObjects == null) return;

        int width = 0;
        int height = 0;

        for(final var childRenderObject : childRenderObjects) {
            childRenderObject.layout(this.getConstraints());

            if(!(childRenderObject instanceof RenderBox renderBox)) throw new ChildWidgetRenderNotSupportedException(
                    childRenderObject, RenderBox.class
            );

            if(direction == Axis.HORIZONTAL) {
                width += renderBox.getSize().width();
                height = Math.max(height, renderBox.getSize().height());
            }else {
                width = Math.max(width, renderBox.getSize().width());
                height += renderBox.getSize().height();
            }
        }

        if(direction == Axis.HORIZONTAL) width += (this.childRenderObjects.size() - 1) * spacing;
        else height += (this.childRenderObjects.size() - 1) * spacing;

        this.setSize(new Size(width, height));
    }

    @Override
    public boolean needsChildLayout() {
        return true;
    }

    @Override
    public void setChildRenderObjects(@NotNull List<@NotNull RenderObject> childRenderObjects) {
        this.childRenderObjects = childRenderObjects;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setDirection(@NotNull Axis direction) {
        this.direction = direction;
    }

    public @NotNull Axis getDirection() {
        return direction;
    }
}
