package com.nyuki.nyuki_backend.todoitem.controller;

import com.nyuki.nyuki_backend.todoitem.service.ToDoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todoitem")
@RequiredArgsConstructor
public class ToDoItemController {

    private final ToDoItemService todoitemService;
}
