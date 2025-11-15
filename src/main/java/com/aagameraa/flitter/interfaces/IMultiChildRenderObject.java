package com.aagameraa.flitter.interfaces;

import com.aagameraa.flitter.material.RenderObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IMultiChildRenderObject {
    void setChildRenderObjects(@NotNull List<@NotNull RenderObject> childRenderObjects);
}
