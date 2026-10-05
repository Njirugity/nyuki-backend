package com.nyuki.nyuki_backend.common.exceptions;

public class GoalNotFoundException extends RuntimeException {
    public GoalNotFoundException(String message) {
        super(message);
    }
}
