package com.nyuki.nyuki_backend.analytics.dto;

public record GoalCountDto(
        long totalGoals,
        long completedGoals,
        long activeGoals,
        long overdueGoals
) {
}
