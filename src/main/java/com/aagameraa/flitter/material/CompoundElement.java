package com.aagameraa.flitter.material;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public abstract class CompoundElement extends Element {
    private @Nullable List<Element> childrens = null;
    public abstract @NotNull List<Element> childrensToAttach();

    public void attachChildrens(@NotNull List<Element> childrens) {
        this.childrens = childrens;
    }

    public @NotNull List<Element> childrens() {
        return Objects.requireNonNull(this.childrens);
    }
}
