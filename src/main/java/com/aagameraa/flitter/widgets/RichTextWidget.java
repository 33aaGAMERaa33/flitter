package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.material.*;
import com.aagameraa.flitter.material.RenderParagraph;
import com.aagameraa.flitter.material.LeafRenderObjectWidget;
import org.jetbrains.annotations.NotNull;

public class RichTextWidget extends LeafRenderObjectWidget {
    private final @NotNull String value;

    public RichTextWidget(@NotNull String value) {
        this.value = value;
    }

    @Override
    public @NotNull RenderObject createRenderObject(@NotNull BuildContext context) {
        return new RenderParagraph(this.value);
    }

    @Override
    public void updateRenderObject(@NotNull RenderObject renderObject) {
        if(!(renderObject instanceof RenderParagraph renderParagraph)) return;

        renderParagraph.setValue(this.value);
        renderParagraph.update();
    }
}
