package com.nyuki.nyuki_backend.focussession.dto;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record CreateFocusSessionDto(
        @NotNull(message = "Schedule required")
        UUID scheduleId,
        Instant startedAt,
        Instant endedAt
) {
}
