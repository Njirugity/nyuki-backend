package com.nyuki.nyuki_backend.strategies.dto;

import java.time.Instant;
import java.util.UUID;

public record StrategyResponseDto(
        UUID id,
        String title,
        String description,
        UUID goalId,
        String goalTitle,
        Instant createdAt,
        Instant updatedAt
) {
}
