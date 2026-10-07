package com.nyuki.nyuki_backend.analytics.dto;

import com.nyuki.nyuki_backend.common.enums.Priority;

import java.util.UUID;

public record TaskCountByGoal(
        UUID goalId,
        String goalTitle,
        Priority priority,
        long totalTasks,
        long completedTasks,
        long activeTasks,
        long notStartedTasks,
        long overDueTasks
) {
}
