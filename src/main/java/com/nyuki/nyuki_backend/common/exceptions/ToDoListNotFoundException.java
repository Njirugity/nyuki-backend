package com.nyuki.nyuki_backend.common.exceptions;

public class ToDoListNotFoundException extends RuntimeException {
    public ToDoListNotFoundException(String message) {
        super(message);
    }
}
