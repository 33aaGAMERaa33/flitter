package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class CompoundWidget extends Widget {
    public abstract void attachWidgets(@NotNull List<Widget> widgets);
    public abstract @NotNull List<Widget> widgetsToAttach();
}
