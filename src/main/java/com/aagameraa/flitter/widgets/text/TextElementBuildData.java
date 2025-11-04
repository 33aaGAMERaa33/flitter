package com.aagameraa.flitter.widgets.text;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public record TextElementBuildData(@NotNull String value, @NotNull FontMetrics fontMetrics,
                                   @NotNull ResourceLocation valueTexture) {
}
