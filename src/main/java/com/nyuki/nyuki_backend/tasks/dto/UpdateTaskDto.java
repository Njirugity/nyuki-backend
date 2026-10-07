package com.nyuki.nyuki_backend.tasks.dto;

import com.nyuki.nyuki_backend.common.enums.Priority;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.UUID;

public record UpdateTaskDto(
        @Pattern(regexp = ".*\\S.*", message = "Title cannot be blank")
        @Size(max = 255, message = "Title must be at most 255 characters")
        String title,
        String description,
        Instant startDate,
        Instant dueDate,
        ProgressStatus status,
        Priority priority,
        UUID goalId,
        UUID strategyId
) {
}
