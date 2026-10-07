package com.nyuki.nyuki_backend.schedule.dto;

import java.time.Instant;

public record UpdateScheduleDto(
        Instant startDateTime,
        Instant endDateTime,
        String notes
) {
}
