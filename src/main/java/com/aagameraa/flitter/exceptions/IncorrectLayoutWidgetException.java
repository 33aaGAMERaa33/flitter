package com.aagameraa.flitter.exceptions;

public class IncorrectLayoutWidgetException extends RuntimeException {
    public IncorrectLayoutWidgetException() {
        super("The widget layout was provided incorrectly");
    }
}
