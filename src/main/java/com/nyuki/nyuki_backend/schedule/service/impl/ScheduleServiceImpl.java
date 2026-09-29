package com.nyuki.nyuki_backend.schedule.service.impl;

import com.nyuki.nyuki_backend.schedule.mapper.ScheduleMapper;
import com.nyuki.nyuki_backend.schedule.repository.ScheduleRepository;
import com.nyuki.nyuki_backend.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleMapper scheduleMapper;
}
