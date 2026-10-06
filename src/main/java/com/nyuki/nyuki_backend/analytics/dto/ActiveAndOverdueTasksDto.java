package com.nyuki.nyuki_backend.analytics.dto;

import java.util.List;

public record ActiveAndOverdueTasksDto(
        List<TaskSummaryDto> activeTasks,
        List<TaskSummaryDto> overdueTasks
) {
}
