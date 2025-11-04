package com.aagameraa.flitter.exceptions;

import com.aagameraa.flitter.material.Element;

public class NoHasParentException extends RuntimeException {
    public NoHasParentException(Element Element) {
        super(String.format("Widget no has parent %s", Element.getClass().getSimpleName()));
    }
}
