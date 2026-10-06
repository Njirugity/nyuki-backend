package com.nyuki.nyuki_backend.analytics.dto;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;

import java.time.LocalDate;
import java.util.UUID;

public record TaskSummaryDto(
        UUID id,
        String title,
        LocalDate dueDate,
        ProgressStatus status,
        String goalTitle) {
}
