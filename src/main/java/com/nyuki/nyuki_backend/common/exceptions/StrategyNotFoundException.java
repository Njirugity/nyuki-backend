package com.nyuki.nyuki_backend.common.exceptions;

public class StrategyNotFoundException extends RuntimeException {
    public StrategyNotFoundException(String message) {
        super(message);
    }
}
