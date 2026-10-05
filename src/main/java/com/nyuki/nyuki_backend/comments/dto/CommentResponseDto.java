package com.nyuki.nyuki_backend.comments.dto;

import java.time.LocalDateTime;

public record CommentResponseDto(
        String content,
        LocalDateTime createdAt,
        String author,
        String taskTitle
) {
}
