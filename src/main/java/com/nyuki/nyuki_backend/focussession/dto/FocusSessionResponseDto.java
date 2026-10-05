package com.nyuki.nyuki_backend.focussession.dto;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record FocusSessionResponseDto(
        UUID id,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        Long duration,
        LocalDateTime pausedAt,
        Long pausedSeconds,
        ProgressStatus status,
        UUID scheduleId,
        UUID taskId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
