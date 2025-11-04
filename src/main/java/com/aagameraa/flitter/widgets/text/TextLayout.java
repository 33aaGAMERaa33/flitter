package com.aagameraa.flitter.widgets.text;

import com.aagameraa.flitter.interfaces.IElementLayout;
import com.aagameraa.flitter.models.BoxConstraints;
import com.aagameraa.flitter.models.Size;
import org.jetbrains.annotations.NotNull;

public class TextLayout implements IElementLayout {
    private final Size size;

    public TextLayout(@NotNull TextElement element, @NotNull BoxConstraints constraints) {
        final var width = element.fontMetrics().stringWidth(element.value());
        final var height = element.fontMetrics().getHeight();

        this.size = new Size(width, height);
    }

    @Override
    public @NotNull Size size() {
        return this.size;
    }
}
