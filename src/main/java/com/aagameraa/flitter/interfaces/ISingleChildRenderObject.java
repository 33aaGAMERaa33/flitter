package com.aagameraa.flitter.interfaces;

import com.aagameraa.flitter.material.RenderObject;
import org.jetbrains.annotations.Nullable;

public interface ISingleChildRenderObject {
    void setChildRenderObject(@Nullable RenderObject childRenderObject);
}
