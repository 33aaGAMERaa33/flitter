package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.material.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Sizedbox extends SingleChildRenderObjectWidget {
    private final @Nullable Integer width;
    private final @Nullable Integer height;

    public Sizedbox() {
        super(null);
        this.width = null;
        this.height = null;
    }

    public Sizedbox(@Nullable Widget child) {
        super(child);
        this.width = null;
        this.height = null;
    }

    public Sizedbox(@Nullable Integer width, @Nullable Integer height) {
        super(null);
        this.width = width;
        this.height = height;
    }

    public Sizedbox(@Nullable Integer width, @Nullable Integer height, @Nullable Widget child) {
        super(child);
        this.width = width;
        this.height = height;
    }

    @Override
    public @NotNull RenderObject createRenderObject(@NotNull BuildContext context) {
        return new RenderSizedbox(this.width, this.height);
    }

    @Override
    public void updateRenderObject(@NotNull RenderObject renderObject) {
        assert renderObject instanceof RenderSizedbox;
        final var renderSizedbox = (RenderSizedbox) renderObject;

        renderSizedbox.setWidth(this.width);
        renderSizedbox.setHeight(this.height);
        renderSizedbox.update();
    }
}
