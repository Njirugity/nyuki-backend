package com.nyuki.nyuki_backend.todolist.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateToDoListDto(
        @Pattern(regexp = ".*\\S.*", message = "Title cannot be blank")
        @Size(max = 255, message = "Title must be at most 255 characters")
        String title,
        UUID taskId
) {
}
