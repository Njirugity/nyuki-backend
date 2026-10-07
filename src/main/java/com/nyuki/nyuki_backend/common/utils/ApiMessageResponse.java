package com.nyuki.nyuki_backend.common.utils;

import java.time.Instant;

public record ApiMessageResponse(
        String message,
        int status,
        Instant timestamp
) {
}
