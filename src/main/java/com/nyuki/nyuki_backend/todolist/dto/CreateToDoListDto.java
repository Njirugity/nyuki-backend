package com.nyuki.nyuki_backend.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateToDoListDto(
        @NotBlank(message = "Title required")
        @Size(max = 255, message = "Title must be at most 255 characters")
        String title,
        UUID taskId
) {
}
