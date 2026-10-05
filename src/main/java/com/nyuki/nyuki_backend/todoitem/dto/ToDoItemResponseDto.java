package com.nyuki.nyuki_backend.todoitem.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ToDoItemResponseDto(
        UUID id,
        String description,
        boolean completed,
        UUID toDoListId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
