package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class CompoundElement extends Element {
    protected @NotNull List<Element> attachedElements = new ArrayList<>();

    public void attachElements(@NotNull List<Element> elements) {
        this.attachedElements = elements;
    }

    public @NotNull List<Element> attachedElements() {
        return this.attachedElements;
    }

    public abstract @NotNull List<Element> elementsToAttach();
}
