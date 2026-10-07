package com.nyuki.nyuki_backend.analytics.dto;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;

import java.time.Instant;
import java.util.UUID;

public record TaskSummaryDto(
        UUID id,
        String title,
        Instant dueDate,
        ProgressStatus status,
        String goalTitle) {
}
