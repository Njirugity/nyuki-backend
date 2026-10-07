package com.nyuki.nyuki_backend.goals.dto;

import com.nyuki.nyuki_backend.common.enums.Priority;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record UpdateGoalDto(
        @Pattern(regexp = ".*\\S.*", message = "Title cannot be blank")
        @Size(max = 255, message = "Title must be at most 255 characters")
        String title,
        String description,
        Instant startDate,
        Instant endDate,
        ProgressStatus status,
        Priority priority
) {
}
