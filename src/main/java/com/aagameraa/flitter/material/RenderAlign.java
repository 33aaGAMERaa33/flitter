package com.aagameraa.flitter.material;

import com.aagameraa.flitter.exceptions.ChildWidgetRenderNotSupportedException;
import com.aagameraa.flitter.interfaces.ISingleChildRenderObject;
import com.aagameraa.flitter.material.renders.RenderBox;
import com.aagameraa.flitter.models.Offset;
import com.aagameraa.flitter.models.Size;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RenderAlign extends RenderBox implements ISingleChildRenderObject {
    private @Nullable RenderObject child;
    private @NotNull Alignment alignment;

    public RenderAlign(@NotNull Alignment alignment) {
        this.alignment = alignment;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, @NotNull Offset offset) {
        if(child == null) return;

        if(!(child instanceof RenderBox box))
            throw new ChildWidgetRenderNotSupportedException(this, RenderBox.class);

        final var childSize = box.getSize();

        int dx = (int) ((this.getConstraints().maxWidth - childSize.width()) * alignment.xFactor);
        int dy = (int) ((this.getConstraints().maxHeight - childSize.height()) * alignment.yFactor);

        box.render(graphics, offset.add(dx, dy));
    }

    @Override
    public void performLayout() {
        if(child == null) {
            this.setSize(new Size(
                    this.getConstraints().maxWidth,
                    this.getConstraints().maxHeight
            ));
            return;
        }

        if(!(child instanceof RenderBox renderBox))
            throw new ChildWidgetRenderNotSupportedException(this, RenderBox.class);

        renderBox.layout(this.getConstraints());
        this.setSize(renderBox.getSize());
    }

    @Override
    public boolean needsChildLayout() {
        return true;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void setChildRenderObject(@Nullable RenderObject childRenderObject) {
        this.child = childRenderObject;
    }

    public void setAlignment(@NotNull Alignment alignment) {
        this.alignment = alignment;
    }
}
