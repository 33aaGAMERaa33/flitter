package com.aagameraa.flitter.material;

import org.jetbrains.annotations.Nullable;

public abstract class Element extends BuildContext {
    private @Nullable Element parent;

    public void mount(@Nullable Element parent) {
        this.parent = parent;
    }

    public @Nullable Element parent() {
        return this.parent;
    }
}
