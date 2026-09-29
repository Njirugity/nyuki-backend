package com.nyuki.nyuki_backend.goals.service.impl;

import com.nyuki.nyuki_backend.goals.mapper.GoalsMapper;
import com.nyuki.nyuki_backend.goals.repository.GoalsRepository;
import com.nyuki.nyuki_backend.goals.service.GoalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoalsServiceImpl implements GoalsService {

    private final GoalsRepository goalsRepository;

    private final GoalsMapper goalsMapper;
}
