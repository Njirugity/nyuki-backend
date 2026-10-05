package com.nyuki.nyuki_backend.comments.service;

import com.nyuki.nyuki_backend.comments.dto.CommentResponseDto;
import com.nyuki.nyuki_backend.comments.dto.CreateCommentDto;

import java.util.List;
import java.util.UUID;

public interface CommentsService {
    CommentResponseDto create(UUID taskId, String email, CreateCommentDto dto);
    List<CommentResponseDto> listComments(UUID taskId, String email);
    void deleteComment(UUID commentId, String email);
}
