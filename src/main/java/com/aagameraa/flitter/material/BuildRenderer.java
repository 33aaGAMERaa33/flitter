package com.aagameraa.flitter.material;

import com.aagameraa.flitter.FlitterRenderer;
import com.aagameraa.flitter.interfaces.IElementLayout;
import com.aagameraa.flitter.interfaces.IElementRenderer;
import com.aagameraa.flitter.models.Position;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

public class BuildRenderer implements IElementRenderer {
    private final @NotNull IElementRenderer renderer;

    public BuildRenderer(
            @NotNull Position position,
            @NotNull Element element,
            @NotNull IElementLayout layout
    ) {
        final var childRendererFactory = FlitterRenderer.getInstance().getElementRendererFactory(element);
        this.renderer = childRendererFactory.buildRenderer(position, element, layout);
    }

    @Override
    public void render(@NotNull GuiGraphics graphics) {
        this.renderer.render(graphics);
    }
}
