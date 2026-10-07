package com.nyuki.nyuki_backend.analytics.dto;

import java.time.Instant;
import java.util.UUID;

public record UpcomingScheduleDto(
        UUID id,
        Instant startDateTime,
        String taskTitle,
        String goalTitle
) {
    public long getRemainingMinutes(Instant now){
        return java.time.Duration.between(now, startDateTime).toMinutes();
    }
}
