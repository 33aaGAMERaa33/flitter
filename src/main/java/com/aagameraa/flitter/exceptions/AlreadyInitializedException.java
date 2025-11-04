package com.aagameraa.flitter.exceptions;

public class AlreadyInitializedException extends RuntimeException {
    public AlreadyInitializedException() {
        super("Already initialized");
    }
}
