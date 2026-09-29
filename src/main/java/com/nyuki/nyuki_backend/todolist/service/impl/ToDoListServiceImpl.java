package com.nyuki.nyuki_backend.todolist.service.impl;

import com.nyuki.nyuki_backend.todolist.mapper.ToDoListMapper;
import com.nyuki.nyuki_backend.todolist.repository.ToDoListRepository;
import com.nyuki.nyuki_backend.todolist.service.ToDoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToDoListServiceImpl implements ToDoListService {

    private final ToDoListRepository todolistRepository;

    private final ToDoListMapper todolistMapper;
}
