package com.aagameraa.flitter.widgets.align;

import com.aagameraa.flitter.material.AdaptativeLayout;
import com.aagameraa.flitter.models.BoxConstraints;
import org.jetbrains.annotations.NotNull;

public class AlignLayout extends AdaptativeLayout {
    public AlignLayout(@NotNull AlignElement element, @NotNull BoxConstraints constraints) {
        super(element.child(), constraints);
    }
}
