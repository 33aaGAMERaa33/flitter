package com.aagameraa.flitter.exceptions;

import com.aagameraa.flitter.material.Element;

public class WidgetLayoutNotFoundException extends RuntimeException {
    public WidgetLayoutNotFoundException(Element Element) {
        super(String.format("Widget layout not found %s", Element.getClass().getSimpleName()));
    }
}
