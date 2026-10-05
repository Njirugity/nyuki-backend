package com.nyuki.nyuki_backend.todoitem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateToDoItemDto(
        @NotBlank(message = "Description required")
        @Size(max = 255, message = "Description must be at most 255 characters")
        String description,
        Boolean completed
) {
}
