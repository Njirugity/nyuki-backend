package com.nyuki.nyuki_backend.analytics.dto;

import com.nyuki.nyuki_backend.common.enums.Priority;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;

import java.time.Instant;
import java.util.UUID;

public record GoalSummaryDto(
        UUID id,
        String title,
        Instant endDate,
        ProgressStatus status,
        Priority priority

) {
}
