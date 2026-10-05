package com.nyuki.nyuki_backend.common.exceptions;

public class ToDoItemNotFoundException extends RuntimeException {
    public ToDoItemNotFoundException(String message) {
        super(message);
    }
}
