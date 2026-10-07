package com.nyuki.nyuki_backend.tasks.dto;

import com.nyuki.nyuki_backend.common.enums.Priority;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.UUID;

public record CreateTaskDto(
        @NotBlank(message = "Title required")
        @Size(max = 255, message = "Title must be at most 255 characters")
        String title,
        String description,
        Instant startDate,
        Instant dueDate,
        ProgressStatus status,
        @NotNull(message = "Priority required")
        Priority priority,
        UUID goalId,
        UUID strategyId
) {
}
