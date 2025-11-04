package com.aagameraa.flitter.widgets.row;

import com.aagameraa.flitter.interfaces.IElementLayout;
import com.aagameraa.flitter.material.AdaptativeLayout;
import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.models.BoxConstraints;
import com.aagameraa.flitter.models.Size;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RowLayout implements IElementLayout {
    private final HashMap<Element, Integer> childTranslateValue = new HashMap<>();
    private final HashMap<Element, IElementLayout> childLayouts = new HashMap<>();
    private final Size totalSize;

    public RowLayout(@NotNull RowElement element, @NotNull BoxConstraints constraints) {
        int width = 0;
        int height = 0;
        int spacing = element.spacing();

        for (int i = 0; i < element.children().size(); i++) {
            final var child = element.children().get(i);
            final var layout = new AdaptativeLayout(child, constraints);
            final var childSize = layout.adaptedElementLayout().size();

            final var translateValue = width + spacing * i;
            height = Math.max(height, childSize.height());
            childTranslateValue.put(child, translateValue);
            childLayouts.put(child, layout.adaptedElementLayout());
            width += childSize.width();
        }

        if (!element.children().isEmpty()) {
            width += spacing * (element.children().size() - 1);
        }

        this.totalSize = new Size(width, height);
    }

    public @NotNull HashMap<Element, Integer> childTranslateValues() {
        return childTranslateValue;
    }

    public @NotNull HashMap<Element, IElementLayout> childLayouts() {
        return childLayouts;
    }

    @Override
    public @NotNull Size size() {
        return totalSize;
    }
}
