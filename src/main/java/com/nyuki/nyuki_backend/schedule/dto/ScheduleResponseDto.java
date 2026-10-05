package com.nyuki.nyuki_backend.schedule.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ScheduleResponseDto(
        UUID id,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        String notes,
        UUID taskId
) {
}
