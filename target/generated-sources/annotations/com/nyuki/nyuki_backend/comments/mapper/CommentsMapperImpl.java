package com.nyuki.nyuki_backend.comments.mapper;

import com.nyuki.nyuki_backend.comments.dto.CommentResponseDto;
import com.nyuki.nyuki_backend.comments.dto.CreateCommentDto;
import com.nyuki.nyuki_backend.comments.dto.UpdateCommentDto;
import com.nyuki.nyuki_backend.comments.entity.Comment;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import com.nyuki.nyuki_backend.users.entity.Users;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-05T17:34:56+0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260826-1225, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class CommentsMapperImpl implements CommentsMapper {

    @Override
    public Comment toComment(CreateCommentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Comment comment = new Comment();

        return comment;
    }

    @Override
    public CommentResponseDto toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        String author = null;
        String taskTitle = null;
        String content = null;
        LocalDateTime createdAt = null;

        author = commentAuthorUserName( comment );
        taskTitle = commentTaskTitle( comment );
        content = comment.getContent();
        createdAt = comment.getCreatedAt();

        CommentResponseDto commentResponseDto = new CommentResponseDto( content, createdAt, author, taskTitle );

        return commentResponseDto;
    }

    @Override
    public void updateEntity(UpdateCommentDto dto, Comment comment) {
        if ( dto == null ) {
            return;
        }

        if ( dto.content() != null ) {
            comment.setContent( dto.content() );
        }
    }

    private String commentAuthorUserName(Comment comment) {
        Users author = comment.getAuthor();
        if ( author == null ) {
            return null;
        }
        return author.getUserName();
    }

    private String commentTaskTitle(Comment comment) {
        Task task = comment.getTask();
        if ( task == null ) {
            return null;
        }
        return task.getTitle();
    }
}
