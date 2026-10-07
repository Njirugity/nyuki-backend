package com.nyuki.nyuki_backend.focussession.dto;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;

import java.time.Instant;
import java.util.UUID;

public record FocusSessionResponseDto(
        UUID id,
        Instant startedAt,
        Instant endedAt,
        Long duration,
        Instant pausedAt,
        Long pausedSeconds,
        ProgressStatus status,
        UUID scheduleId,
        UUID taskId,
        Instant createdAt,
        Instant updatedAt
) {
}
