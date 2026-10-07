package com.nyuki.nyuki_backend.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.UUID;

public record CreateScheduleDto(
        Instant startDateTime,
        Instant endDateTime,
        String notes
) {
}
