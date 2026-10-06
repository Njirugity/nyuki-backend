package com.nyuki.nyuki_backend.analytics.dto;

import java.util.List;

public record DashboardResponseDto(
        ActiveAndOverdueTasksDto activeAndOverdueTasksDto,
        GoalCountDto goalCountDto,
        TaskCountDto taskCountDto,
        List<UpcomingScheduleDto> upcomingScheduleDto
) {
}
