package com.aagameraa.flitter.widgets;

import com.aagameraa.flitter.interfaces.IWidget;
import com.aagameraa.flitter.material.BuildContext;
import com.aagameraa.flitter.material.StatelessWidget;
import com.aagameraa.flitter.widgets.align.AlignWidget;
import com.aagameraa.flitter.widgets.align.Alignment;
import org.jetbrains.annotations.NotNull;

public class CenterWidget extends StatelessWidget {
    private final @NotNull IWidget child;

    public CenterWidget(@NotNull IWidget child) {
        this.child = child;
    }

    @Override
    public @NotNull IWidget build(BuildContext context) {
        return new AlignWidget(Alignment.CENTER_CENTER, child);
    }
}
