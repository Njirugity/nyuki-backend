package com.nyuki.nyuki_backend.todoitem.service.impl;

import com.nyuki.nyuki_backend.todoitem.mapper.ToDoItemMapper;
import com.nyuki.nyuki_backend.todoitem.repository.ToDoItemRepository;
import com.nyuki.nyuki_backend.todoitem.service.ToDoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToDoItemServiceImpl implements ToDoItemService {

    private final ToDoItemRepository todoitemRepository;

    private final ToDoItemMapper todoitemMapper;
}
