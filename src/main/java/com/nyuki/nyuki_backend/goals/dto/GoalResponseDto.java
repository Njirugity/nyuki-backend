package com.nyuki.nyuki_backend.goals.dto;

import com.nyuki.nyuki_backend.common.enums.Priority;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record GoalResponseDto(
        UUID id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        ProgressStatus status,
        Priority priority,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
