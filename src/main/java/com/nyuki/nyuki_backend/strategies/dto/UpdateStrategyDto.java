package com.nyuki.nyuki_backend.strategies.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateStrategyDto(
        @Pattern(regexp = ".*\\S.*", message = "Title cannot be blank")
        @Size(max = 255, message = "Title must be at most 255 characters")
        String title,
        String description,
        UUID goalId
) {
}
