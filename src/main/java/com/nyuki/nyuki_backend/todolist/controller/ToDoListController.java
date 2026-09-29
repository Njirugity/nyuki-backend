package com.nyuki.nyuki_backend.todolist.controller;

import com.nyuki.nyuki_backend.todolist.service.ToDoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todolist")
@RequiredArgsConstructor
public class ToDoListController {

    private final ToDoListService todolistService;
}
