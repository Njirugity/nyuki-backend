package com.nyuki.nyuki_backend.schedule.dto;

import java.time.LocalDateTime;

public record UpdateScheduleDto(
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        String notes
) {
}
