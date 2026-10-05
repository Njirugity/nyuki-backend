package com.nyuki.nyuki_backend.common.exceptions;

public class FocusSessionNotFoundException extends RuntimeException {
    public FocusSessionNotFoundException(String message) {
        super(message);
    }
}
