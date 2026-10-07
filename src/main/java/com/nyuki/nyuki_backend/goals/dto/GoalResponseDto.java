package com.nyuki.nyuki_backend.goals.dto;

import com.nyuki.nyuki_backend.common.enums.Priority;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;

import java.time.Instant;
import java.util.UUID;

public record GoalResponseDto(
        UUID id,
        String title,
        String description,
        Instant startDate,
        Instant endDate,
        ProgressStatus status,
        Priority priority,
        Instant createdAt,
        Instant updatedAt
) {
}
