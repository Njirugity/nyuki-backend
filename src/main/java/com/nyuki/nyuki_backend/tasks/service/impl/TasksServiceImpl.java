package com.nyuki.nyuki_backend.tasks.service.impl;

import com.nyuki.nyuki_backend.tasks.mapper.TasksMapper;
import com.nyuki.nyuki_backend.tasks.repository.TasksRepository;
import com.nyuki.nyuki_backend.tasks.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {

    private final TasksRepository tasksRepository;

    private final TasksMapper tasksMapper;
}
