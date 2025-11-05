package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Stack;

public class BuildTree {
    private boolean mounted;
    private final Stack<Widget> widgets;
    private final Stack<BuildTree> buildTrees;

    public BuildTree() {
        this.mounted = true;
        this.widgets = new Stack<>();
        this.buildTrees = new Stack<>();
    }

    public BuildTree(@NotNull BuildTree buildTree) {
        this();
        widgets.addAll(buildTree.widgets);
        buildTrees.addAll(buildTree.buildTrees);
    }

    public @NotNull BuildTree pushNewBuildTree() {
        final var newBuildTree = new BuildTree();
        this.buildTrees.push(newBuildTree);

        return newBuildTree;
    }

    public <T extends Widget> void pushWidget(T widget) {
        this.widgets.push(widget);
    }

    public void popWidget() {
        if(this.widgets.empty()) return;
       this.widgets.pop();
    }

    public Stack<BuildTree> getBuildTreesStack() {
        return buildTrees;
    }

    public Stack<Widget> getWidgetsStack() {
        return widgets;
    }

    public int countAllWidgets() {
        int total = widgets.size();

        for (BuildTree childBuildTree : buildTrees) {
            total += childBuildTree.countAllWidgets();
        }

        return total;
    }

    public void dispose() {
        if(!this.mounted) return;
        this.mounted = false;

    }

    public boolean mounted() {
        return mounted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BuildTree c)) return false;
        return widgets.equals(c.widgets) && buildTrees.equals(c.buildTrees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(widgets.hashCode(), buildTrees.hashCode());
    }

    @Override
    public String toString() {
        return String.format(
                "BuildTree: \n%s: %d\n%s: %d",
                "WidgetsStack", widgets.size(),
                "BuildTreesStack", buildTrees.size()
        );
    }
}
