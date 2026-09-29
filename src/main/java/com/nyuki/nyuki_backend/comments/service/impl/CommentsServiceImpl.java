package com.nyuki.nyuki_backend.comments.service.impl;

import com.nyuki.nyuki_backend.comments.mapper.CommentsMapper;
import com.nyuki.nyuki_backend.comments.repository.CommentsRepository;
import com.nyuki.nyuki_backend.comments.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;

    private final CommentsMapper commentsMapper;
}
