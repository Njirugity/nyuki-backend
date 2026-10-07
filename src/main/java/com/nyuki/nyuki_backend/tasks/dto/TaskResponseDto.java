package com.nyuki.nyuki_backend.tasks.dto;

import com.nyuki.nyuki_backend.common.enums.Priority;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;

import java.time.Instant;
import java.util.UUID;

public record TaskResponseDto(
        UUID id,
        String title,
        String description,
        Instant startDate,
        Instant dueDate,
        Instant completedAt,
        ProgressStatus status,
        Priority priority,
        UUID goalId,
        UUID strategyId,
        Instant createdAt,
        Instant updatedAt
) {
}
