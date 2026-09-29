package com.nyuki.nyuki_backend.strategies.controller;

import com.nyuki.nyuki_backend.strategies.service.StrategiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/strategies")
@RequiredArgsConstructor
public class StrategiesController {

    private final StrategiesService strategiesService;
}
