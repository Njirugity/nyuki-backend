package com.nyuki.nyuki_backend.focustimer.service.impl;

import com.nyuki.nyuki_backend.focustimer.mapper.FocusTimerMapper;
import com.nyuki.nyuki_backend.focustimer.repository.FocusTimerRepository;
import com.nyuki.nyuki_backend.focustimer.service.FocusTimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FocusTimerServiceImpl implements FocusTimerService {

    private final FocusTimerRepository focustimerRepository;

    private final FocusTimerMapper focustimerMapper;
}
