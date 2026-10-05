package com.nyuki.nyuki_backend.todolist.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ToDoListResponseDto(
        UUID id,
        String title,
        UUID taskId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
