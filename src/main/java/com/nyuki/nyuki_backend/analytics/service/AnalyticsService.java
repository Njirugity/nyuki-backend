package com.nyuki.nyuki_backend.analytics.service;

import com.nyuki.nyuki_backend.analytics.dto.*;

import java.time.Instant;
import java.util.List;

public interface AnalyticsService {
    GoalCountDto getGoalsCount(String email, Instant now);
    ActiveAndOverdueGoalsDto getActiveAndOverdueGoals(String email);
    List<TaskCountByGoal> getTaskCountByGoal(String email);
    ActiveAndOverdueTasksDto getActiveAndOverdueTasks(String email, Instant now);
    TaskCountDto getTaskCount(String email, Instant now);
    List<UpcomingScheduleDto> getAlmostDueSchedules(String email, Instant now, Instant windowEnd);
    DashboardResponseDto getDashboardResponse(String email);
}
