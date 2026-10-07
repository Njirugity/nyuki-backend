package com.nyuki.nyuki_backend.comments.dto;

import java.time.Instant;

public record CommentResponseDto(
        String content,
        Instant createdAt,
        String author,
        String taskTitle
) {
}
