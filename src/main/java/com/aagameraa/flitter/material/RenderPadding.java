package com.aagameraa.flitter.material;

import com.aagameraa.flitter.interfaces.ISingleChildRenderObject;
import com.aagameraa.flitter.models.Offset;
import com.aagameraa.flitter.models.Size;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RenderPadding extends RenderBox implements ISingleChildRenderObject {
    private @NotNull Padding padding;
    private @Nullable RenderBox renderBox;

    public RenderPadding(@NotNull Padding padding) {
        this.padding = padding;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, @NotNull Offset offset) {
        assert this.renderBox != null;
        this.renderBox.render(graphics, offset.add(padding.left(), padding.top()));
    }

    @Override
    protected void performLayout() {
        assert this.renderBox != null;
        this.renderBox.layout(this.getConstraints());

        this.setSize(new Size(
                this.renderBox.getSize().width() + padding.left() + padding.right(),
                this.renderBox.getSize().height() + padding.top() + padding.bottom()
        ));
    }

    public void setPadding(@NotNull Padding padding) {
        this.padding = padding;
    }

    @Override
    public boolean needsChildLayout() {
        return true;
    }

    @Override
    public void setChildRenderObject(@Nullable RenderObject childRenderObject) {
        assert childRenderObject instanceof RenderBox;
        this.renderBox = (RenderBox) childRenderObject;
    }
}
