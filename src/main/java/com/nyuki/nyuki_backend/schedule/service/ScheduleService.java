package com.nyuki.nyuki_backend.schedule.service;

import com.nyuki.nyuki_backend.schedule.dto.CreateScheduleDto;
import com.nyuki.nyuki_backend.schedule.dto.ScheduleResponseDto;
import com.nyuki.nyuki_backend.schedule.dto.UpdateScheduleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ScheduleService {
    ScheduleResponseDto create(UUID taskId, CreateScheduleDto dto, String email);
    ScheduleResponseDto getSchedule(UUID scheduleId, String email);
    Page<ScheduleResponseDto> listSchedules(String email, UUID taskId, Pageable pageable);
    ScheduleResponseDto updateSchedule(UUID scheduleId, UpdateScheduleDto dto, String email);
    void deleteSchedule(UUID scheduleId, String email);
}
