package com.nyuki.nyuki_backend.focussession.dto;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;

import java.time.Instant;

public record UpdateFocusSessionDto(
        Instant startedAt,
        Instant endedAt,
        ProgressStatus status
) {
}
