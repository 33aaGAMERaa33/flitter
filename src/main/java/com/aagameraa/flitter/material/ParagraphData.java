package com.aagameraa.flitter.material;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public record ParagraphData(
        @NotNull String value,
        @NotNull FontMetrics fontMetrics,
        @NotNull ResourceLocation valueTexture
) {
}
