package com.nyuki.nyuki_backend.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateScheduleDto(
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        String notes
) {
}
