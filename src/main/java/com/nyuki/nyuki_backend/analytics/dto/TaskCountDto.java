package com.nyuki.nyuki_backend.analytics.dto;

public record TaskCountDto(
        long totalTasks,
        long completedTasks,
        long activeTasks,
        long overDueTasks
) {
}
