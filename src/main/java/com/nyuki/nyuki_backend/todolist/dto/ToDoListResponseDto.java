package com.nyuki.nyuki_backend.todolist.dto;

import java.time.Instant;
import java.util.UUID;

public record ToDoListResponseDto(
        UUID id,
        String title,
        UUID taskId,
        Instant createdAt,
        Instant updatedAt
) {
}
