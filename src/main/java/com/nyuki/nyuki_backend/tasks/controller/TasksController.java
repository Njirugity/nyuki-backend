package com.nyuki.nyuki_backend.tasks.controller;

import com.nyuki.nyuki_backend.tasks.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksService tasksService;
}
