package com.aagameraa.flitter.exceptions;

import com.aagameraa.flitter.material.Element;

public class ElementFactoryNotFoundedException extends RuntimeException {
    public ElementFactoryNotFoundedException(Element element) {
        super(String.format("Element factory not founded \nElement: %s\n Widget: %s", element.getClass().getSimpleName(), element.widget().getClass().getSimpleName()));
    }
}
