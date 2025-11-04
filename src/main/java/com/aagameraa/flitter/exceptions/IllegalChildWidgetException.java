package com.aagameraa.flitter.exceptions;

import com.aagameraa.flitter.material.Element;

public class IllegalChildWidgetException extends RuntimeException {
    public IllegalChildWidgetException(Element Element) {
        super(String.format("Illegal child widget %s", Element.getClass().getSimpleName()));
    }
}
