package com.aagameraa.flitter.material;

import com.aagameraa.flitter.models.Constraints;
import com.aagameraa.flitter.models.Offset;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

public abstract class RenderObject {
    public abstract void render(@NotNull GuiGraphics graphics, @NotNull Offset offset);
    public abstract void update();
    public abstract void layout(@NotNull Constraints constraints);
}
