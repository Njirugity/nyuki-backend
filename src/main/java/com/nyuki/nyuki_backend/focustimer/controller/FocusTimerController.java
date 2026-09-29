package com.nyuki.nyuki_backend.focustimer.controller;

import com.nyuki.nyuki_backend.focustimer.service.FocusTimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/focustimer")
@RequiredArgsConstructor
public class FocusTimerController {

    private final FocusTimerService focustimerService;
}
