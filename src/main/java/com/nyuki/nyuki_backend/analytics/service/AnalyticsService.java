package com.nyuki.nyuki_backend.analytics.service;

import com.nyuki.nyuki_backend.analytics.dto.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AnalyticsService {
    GoalCountDto getGoalsCount(String email, LocalDate today);
    ActiveAndOverdueGoalsDto getActiveAndOverdueGoals(String email);
    List<TaskCountByGoal> getTaskCountByGoal(String email);
    ActiveAndOverdueTasksDto getActiveAndOverdueTasks(String email, LocalDate today);
    TaskCountDto getTaskCount(String email, LocalDate today);
    List<UpcomingScheduleDto> getAlmostDueSchedules(String email, LocalDateTime now, LocalDateTime windowEnd);
    DashboardResponseDto getDashboardResponse(String email);
}
