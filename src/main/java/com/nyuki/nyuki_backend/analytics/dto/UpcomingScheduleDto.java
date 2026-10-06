package com.nyuki.nyuki_backend.analytics.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpcomingScheduleDto(
        UUID id,
        LocalDateTime startDateTime,
        String taskTitle,
        String goalTitle
) {
    public long getRemainingMinutes(LocalDateTime now){
        return java.time.Duration.between(now, startDateTime).toMinutes();
    }
}
