package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.material.Alignment;
import com.aagameraa.flitter.material.Widget;
import org.jetbrains.annotations.Nullable;

public class CenterWidget extends AlignWidget {
    public CenterWidget(@Nullable Widget child) {
        super(Alignment.CENTER, child);
    }
}
