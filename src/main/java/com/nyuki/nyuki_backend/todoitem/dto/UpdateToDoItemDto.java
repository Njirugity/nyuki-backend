package com.nyuki.nyuki_backend.todoitem.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateToDoItemDto(
        @Pattern(regexp = ".*\\S.*", message = "Description cannot be blank")
        @Size(max = 255, message = "Description must be at most 255 characters")
        String description,
        Boolean completed
) {
}
