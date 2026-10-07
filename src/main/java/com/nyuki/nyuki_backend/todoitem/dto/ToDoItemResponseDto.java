package com.nyuki.nyuki_backend.todoitem.dto;

import java.time.Instant;
import java.util.UUID;

public record ToDoItemResponseDto(
        UUID id,
        String description,
        boolean completed,
        UUID toDoListId,
        Instant createdAt,
        Instant updatedAt
) {
}
