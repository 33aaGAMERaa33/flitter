package com.aagameraa.flitter.widgets.column;

import com.aagameraa.flitter.interfaces.IElementLayout;
import com.aagameraa.flitter.material.AdaptativeLayout;
import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.models.BoxConstraints;
import com.aagameraa.flitter.models.Size;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ColumnLayout implements IElementLayout {
    private final HashMap<Element, Integer> childTranslateValue = new HashMap<>();
    private final HashMap<Element, IElementLayout> childLayouts = new HashMap<>();
    private final Size totalSize;

    public ColumnLayout(@NotNull ColumnElement element, @NotNull BoxConstraints constraints) {
        int width = 0;
        int height = 0;
        int spacing = element.spacing();

        for(int i = 0; i < element.children().size(); i++) {
            final var child = element.children().get(i);
            final var layout = new AdaptativeLayout(child, constraints);
            Size childSize = layout.adaptedElementLayout().size();

            final var translateValue = height + spacing * i;
            width = Math.max(width, childSize.width());
            childTranslateValue.put(child, translateValue);
            childLayouts.put(child, layout.adaptedElementLayout());
            height += childSize.height();
        }

        if(!element.children().isEmpty()) {
            height += spacing * (element.children().size() - 1);
        }

        this.totalSize = new Size(width, height);
    }

    public @NotNull HashMap<Element, Integer> childTranslateValues() {
        return childTranslateValue;
    }

    public HashMap<Element, IElementLayout> childLayouts() {
        return childLayouts;
    }

    @Override
    public @NotNull Size size() {
        return totalSize;
    }
}
