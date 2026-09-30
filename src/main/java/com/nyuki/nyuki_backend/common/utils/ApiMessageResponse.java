package com.nyuki.nyuki_backend.common.utils;

import java.time.LocalDate;

public record ApiMessageResponse(
        String message,
        int status,
        LocalDate timestamp
) {
}
