package com.nyuki.nyuki_backend.comments.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCommentDto(
        @NotBlank(message = "Comment required")
        @Size(max = 255, message = "Comment must be at most 255 characters")
        String comment
) {
}
