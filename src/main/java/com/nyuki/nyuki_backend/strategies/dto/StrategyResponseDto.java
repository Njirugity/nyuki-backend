package com.nyuki.nyuki_backend.strategies.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record StrategyResponseDto(
        UUID id,
        String title,
        String description,
        UUID goalId,
        String goalTitle,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
