package com.nyuki.nyuki_backend.comments.mapper;

import com.nyuki.nyuki_backend.comments.dto.CommentResponseDto;
import com.nyuki.nyuki_backend.comments.dto.CreateCommentDto;
import com.nyuki.nyuki_backend.comments.dto.UpdateCommentDto;
import com.nyuki.nyuki_backend.comments.entity.Comment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CommentsMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "task", ignore = true)
    Comment toComment(CreateCommentDto dto);
    @Mapping(source = "author.userName", target = "author")
    @Mapping(source = "task.title", target = "taskTitle")
    CommentResponseDto toDto(Comment comment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "task", ignore = true)
    void updateEntity(UpdateCommentDto dto, @MappingTarget Comment comment);
}
