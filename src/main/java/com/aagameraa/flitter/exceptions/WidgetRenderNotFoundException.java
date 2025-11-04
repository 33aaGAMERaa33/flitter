package com.aagameraa.flitter.exceptions;

import com.aagameraa.flitter.material.Element;

public class WidgetRenderNotFoundException extends RuntimeException {
    public <T extends Element> WidgetRenderNotFoundException(T widget) {
        super(String.format("WidgetRender not found: %s", widget.getClass().getSimpleName()));
    }
}
