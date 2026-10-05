package com.nyuki.nyuki_backend.strategies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateStrategyDto(
        @NotBlank(message = "Title required")
        @Size(max = 255, message = "Title must be at most 255 characters")
        String title,
        String description,
        @NotNull(message = "Goal required")
        UUID goalId
) {
}
