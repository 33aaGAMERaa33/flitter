package com.aagameraa.flitter.material;

import java.util.Objects;
import java.util.Stack;

public class BuildTree {
    public final Stack<Widget> widgets;
    public final Stack<BuildTree> subBuildTrees;

    public BuildTree() {
        this.widgets = new Stack<>();
        this.subBuildTrees = new Stack<>();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BuildTree other)) return false;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(widgets.hashCode(), subBuildTrees.hashCode());
    }
}
