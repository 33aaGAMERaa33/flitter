package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.material.*;
import com.aagameraa.flitter.material.RenderFlex;
import com.aagameraa.flitter.material.MultiChildRenderObjectWidget;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FlexWidget extends MultiChildRenderObjectWidget {
    private final int spacing;
    private final @NotNull Axis direction;

    public FlexWidget(
            @NotNull List<Widget> childrens,
            @NotNull Axis direction,
            int spacing
    ) {
        super(childrens);
        this.spacing = spacing;
        this.direction = direction;
    }

    @Override
    public @NotNull RenderObject createRenderObject(@NotNull BuildContext context) {
        return new RenderFlex(direction, spacing);
    }

    @Override
    public void updateRenderObject(@NotNull RenderObject renderObject) {
        if(!(renderObject instanceof RenderFlex renderFlex)) return;

        renderFlex.setSpacing(this.spacing);
        renderFlex.setDirection(this.direction);
        renderFlex.update();
    }

    public int getSpacing() {
        return spacing;
    }

    public @NotNull Axis getDirection() {
        return direction;
    }

    public static class Builder {
        private int spacing = 0;
        private final @NotNull List<Widget> childrens;
        private @NotNull Axis direction = Axis.HORIZONTAL;

        public Builder(@NotNull List<Widget> childrens) {
            this.childrens = childrens;
        }

        public Builder direction(@NotNull Axis direction) {
            this.direction = direction;
            return this;
        }

        public Builder spacing(int spacing) {
            this.spacing = spacing;
            return this;
        }

        public FlexWidget build() {
            return new FlexWidget(this.childrens, this.direction, this.spacing);
        }
    }
}
