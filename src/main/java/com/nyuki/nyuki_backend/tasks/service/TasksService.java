package com.nyuki.nyuki_backend.tasks.service;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.tasks.dto.CreateTaskDto;
import com.nyuki.nyuki_backend.tasks.dto.TaskResponseDto;
import com.nyuki.nyuki_backend.tasks.dto.UpdateTaskDto;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TasksService {
    TaskResponseDto create(CreateTaskDto dto, String email);
    TaskResponseDto getTask(UUID taskId, String email);
    Page<TaskResponseDto> getTasks(String email, String search, UUID goalId, UUID strategyId,
                                   ProgressStatus status, Pageable pageable);
    TaskResponseDto update(UUID taskId, UpdateTaskDto dto, String email);
    void delete(UUID taskId, String email);
    Task findOwnedTask(UUID taskId, String email);
}
