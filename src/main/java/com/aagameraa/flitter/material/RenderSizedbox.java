package com.aagameraa.flitter.material;

import com.aagameraa.flitter.interfaces.ISingleChildRenderObject;
import com.aagameraa.flitter.models.Offset;
import com.aagameraa.flitter.models.Size;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RenderSizedbox extends RenderBox implements ISingleChildRenderObject {
    private @Nullable Integer width;
    private @Nullable Integer height;
    private @Nullable RenderBox renderBox = null;

    public RenderSizedbox(@Nullable Integer width, @Nullable Integer height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, @NotNull Offset offset) {
        if(this.renderBox == null) return;
        this.renderBox.render(graphics, offset);
    }

    @Override
    protected void performLayout() {
        int width = 0;
        int height = 0;
        if(this.renderBox != null) this.renderBox.layout(this.getConstraints());

        if(this.width != null) width = this.width;
        else if(this.renderBox != null) {
            width = this.renderBox.getSize().width();
        }

        if(this.height != null) height = this.height;
        else if(this.renderBox != null) {
            height = this.renderBox.getSize().height();
        }

        this.setSize(new Size(width, height));
    }

    @Override
    public boolean needsChildLayout() {
        return true;
    }

    @Override
    public void update() {
        super.update();
        this.performLayout();
    }

    public void setWidth(@Nullable Integer width) {
        this.width = width;
    }

    public void setHeight(@Nullable Integer height) {
        this.height = height;
    }

    @Override
    public void setChildRenderObject(@Nullable RenderObject childRenderObject) {
        assert childRenderObject instanceof RenderBox;
        this.renderBox = (RenderBox) childRenderObject;
    }

    public static class Builder {
        private @Nullable Integer width = null;
        private @Nullable Integer height = null;

        public Builder width(@Nullable Integer width) {
            this.width = width;
            return this;
        }

        public Builder height(@Nullable Integer height) {
            this.height = height;
            return this;
        }

        public RenderSizedbox build() {
            return new RenderSizedbox(this.width, this.height);
        }
    }
}
