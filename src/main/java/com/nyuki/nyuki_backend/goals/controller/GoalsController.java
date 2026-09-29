package com.nyuki.nyuki_backend.goals.controller;

import com.nyuki.nyuki_backend.goals.service.GoalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class GoalsController {

    private final GoalsService goalsService;
}
