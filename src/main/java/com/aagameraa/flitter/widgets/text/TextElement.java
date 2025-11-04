package com.aagameraa.flitter.widgets.text;

import com.aagameraa.flitter.material.Element;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class TextElement extends Element {
    private final @NotNull String value;
    private final @NotNull FontMetrics fontMetrics;
    private final @NotNull ResourceLocation valueTexture;

    public TextElement(
            @NotNull String value,
            @NotNull FontMetrics fontMetrics,
            @NotNull ResourceLocation valueTexture
    ) {
        this.value = value;
        this.fontMetrics = fontMetrics;
        this.valueTexture = valueTexture;
    }

    public @NotNull String value() {
        return value;
    }

    public @NotNull FontMetrics fontMetrics() {
        return fontMetrics;
    }

    public @NotNull ResourceLocation valueTexture() {
        return valueTexture;
    }

    @Override
    public void mount(@NotNull Element parent) {

    }

    @Override
    public @Nullable Element parent() {
        return null;
    }
}
