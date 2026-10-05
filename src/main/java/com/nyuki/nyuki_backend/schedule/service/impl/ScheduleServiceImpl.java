package com.nyuki.nyuki_backend.schedule.service.impl;

import com.nyuki.nyuki_backend.common.exceptions.InvalidRequestException;
import com.nyuki.nyuki_backend.common.exceptions.ScheduleNotFoundException;
import com.nyuki.nyuki_backend.common.exceptions.UserNotFoundException;
import com.nyuki.nyuki_backend.schedule.dto.CreateScheduleDto;
import com.nyuki.nyuki_backend.schedule.dto.ScheduleResponseDto;
import com.nyuki.nyuki_backend.schedule.dto.UpdateScheduleDto;
import com.nyuki.nyuki_backend.schedule.entity.Schedule;
import com.nyuki.nyuki_backend.schedule.mapper.ScheduleMapper;
import com.nyuki.nyuki_backend.schedule.repository.ScheduleRepository;
import com.nyuki.nyuki_backend.schedule.service.ScheduleService;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import com.nyuki.nyuki_backend.tasks.service.TasksService;
import com.nyuki.nyuki_backend.users.entity.Users;
import com.nyuki.nyuki_backend.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final TasksService tasksService;
    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public ScheduleResponseDto create(UUID taskId, CreateScheduleDto dto, String email){
        Users owner = usersRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User not found")
        );
        Task task = tasksService.findOwnedTask(taskId, email);
        Schedule schedule = scheduleMapper.toSchedule(dto);
        schedule.setTask(task);
        schedule.setOwner(owner);
        scheduleRepository.save(schedule);
        return scheduleMapper.toDto(schedule);
    }
    @Override
    public ScheduleResponseDto getSchedule(UUID scheduleId, String email){
        return scheduleMapper.toDto(findOwnedSchedule(scheduleId, email));
    }
    @Override
    public Page<ScheduleResponseDto> listSchedules(String email, UUID taskId, Pageable pageable){
        return scheduleRepository.listSchedules(email, taskId, pageable)
                .map(scheduleMapper::toDto);
    }

    @Override
    @Transactional
    public ScheduleResponseDto updateSchedule(UUID scheduleId, UpdateScheduleDto dto, String email){
        Schedule schedule = findOwnedSchedule(scheduleId, email);
        scheduleMapper.updateSchedule(dto, schedule);
        scheduleRepository.save(schedule);
        return scheduleMapper.toDto(schedule);
    }
    @Override
    @Transactional
    public void deleteSchedule(UUID scheduleId, String email){
        scheduleRepository.delete(findOwnedSchedule(scheduleId, email));
    }
    private Schedule findOwnedSchedule(UUID scheduleId, String email){
        return scheduleRepository.findByIdAndOwnerEmail(scheduleId, email).
                orElseThrow(()-> new ScheduleNotFoundException("Schedule not found"));
    }
}
