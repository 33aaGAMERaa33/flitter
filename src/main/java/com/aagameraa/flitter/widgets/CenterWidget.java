package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.material.BuildContext;
import com.aagameraa.flitter.material.StatelessWidget;
import com.aagameraa.flitter.material.Widget;
import com.aagameraa.flitter.widgets.align.AlignWidget;
import com.aagameraa.flitter.widgets.align.Alignment;
import org.jetbrains.annotations.NotNull;

public class CenterWidget extends StatelessWidget {
    private final @NotNull Widget child;

    public CenterWidget(@NotNull Widget child) {
        this.child = child;
    }

    @Override
    public @NotNull Widget build(@NotNull BuildContext context) {
        return new AlignWidget(
                Alignment.CENTER_CENTER,
                this.child
        );
    }
}
