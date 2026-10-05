package com.nyuki.nyuki_backend.tasks.dto;

import com.nyuki.nyuki_backend.common.enums.Priority;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponseDto(
        UUID id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate dueDate,
        LocalDate completedAt,
        ProgressStatus status,
        Priority priority,
        UUID goalId,
        UUID strategyId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
