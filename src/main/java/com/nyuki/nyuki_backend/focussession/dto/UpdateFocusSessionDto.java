package com.nyuki.nyuki_backend.focussession.dto;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;

import java.time.LocalDateTime;

public record UpdateFocusSessionDto(
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        ProgressStatus status
) {
}
