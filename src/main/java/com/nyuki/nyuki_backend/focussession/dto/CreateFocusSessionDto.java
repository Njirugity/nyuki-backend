package com.nyuki.nyuki_backend.focussession.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateFocusSessionDto(
        @NotNull(message = "Schedule required")
        UUID scheduleId,
        LocalDateTime startedAt,
        LocalDateTime endedAt
) {
}
