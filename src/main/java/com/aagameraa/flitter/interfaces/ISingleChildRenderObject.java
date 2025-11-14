package com.aagameraa.flitter.interfaces;

import com.aagameraa.flitter.material.RenderObject;
import org.jetbrains.annotations.NotNull;

public interface ISingleChildRenderObject {
    void setChildRenderObject(@NotNull RenderObject renderObject);
}
