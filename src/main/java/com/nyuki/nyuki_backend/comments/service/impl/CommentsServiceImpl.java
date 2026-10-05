package com.nyuki.nyuki_backend.comments.service.impl;

import com.nyuki.nyuki_backend.comments.dto.CommentResponseDto;
import com.nyuki.nyuki_backend.comments.dto.CreateCommentDto;
import com.nyuki.nyuki_backend.comments.entity.Comment;
import com.nyuki.nyuki_backend.comments.mapper.CommentsMapper;
import com.nyuki.nyuki_backend.comments.repository.CommentsRepository;
import com.nyuki.nyuki_backend.comments.service.CommentsService;
import com.nyuki.nyuki_backend.common.exceptions.UserNotFoundException;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import com.nyuki.nyuki_backend.tasks.service.TasksService;
import com.nyuki.nyuki_backend.users.entity.Users;
import com.nyuki.nyuki_backend.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final CommentsMapper commentsMapper;
    private final TasksService tasksService;
    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public CommentResponseDto create(UUID taskId, String email, CreateCommentDto dto){
        Task task = tasksService.findOwnedTask(taskId, email);
        Users author = usersRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User not found")
        );
        Comment comment = commentsMapper.toComment(dto);
        comment.setAuthor(author);
        comment.setTask(task);
        commentsRepository.save(comment);
        return commentsMapper.toDto(comment);
    }
    @Override
    public List<CommentResponseDto> listComments(UUID taskId, String email){
        Task task = tasksService.findOwnedTask(taskId, email);

        return commentsRepository.findByTaskOrderByCreatedAtAsc(task)
                .stream()
                .map(commentsMapper::toDto).toList();
    }
    @Override
    @Transactional
    public void deleteComment(UUID commentId, String email){
        Comment comment = findOwnedComment(commentId, email);
        commentsRepository.delete(comment);
    }

    private Comment findOwnedComment(UUID commentId, String email){
       return commentsRepository.findByIdAndAuthorEmail(commentId, email).
                orElseThrow(()-> new UserNotFoundException("User not found"));
    }
}
