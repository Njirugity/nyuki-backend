package com.nyuki.nyuki_backend.schedule.dto;

import java.time.Instant;
import java.util.UUID;

public record ScheduleResponseDto(
        UUID id,
        Instant startDateTime,
        Instant endDateTime,
        String notes,
        UUID taskId
) {
}
