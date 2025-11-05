package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

public class FlitterApp {
    private final @NotNull BuildTree buildTree;

    public FlitterApp(@NotNull BuildTree buildTree) {
        this.buildTree = buildTree;
    }

    public void pushWidget(Widget widget) {
        buildTree.pushWidget(widget);
    }
}
