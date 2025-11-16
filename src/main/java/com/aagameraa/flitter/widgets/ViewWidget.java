package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.material.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewWidget extends MultiChildRenderObjectWidget {
    private final @NotNull List<Widget> childrens;

    public ViewWidget(@NotNull List<Widget> childrens) {
        super(childrens);
        this.childrens = childrens;
    }

    @Override
    public @NotNull RenderObject createRenderObject(@NotNull BuildContext context) {
        return new RenderView();
    }

    @Override
    public void updateRenderObject(@NotNull RenderObject renderObject) {
        renderObject.update();
    }

    @Override
    public @NotNull List<Widget> getChildrens() {
        return childrens;
    }
}
