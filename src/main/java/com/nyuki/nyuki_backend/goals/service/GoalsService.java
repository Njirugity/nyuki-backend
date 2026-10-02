package com.nyuki.nyuki_backend.goals.service;

import com.nyuki.nyuki_backend.goals.dto.CreateGoalDto;
import com.nyuki.nyuki_backend.goals.dto.GoalResponseDto;
import com.nyuki.nyuki_backend.goals.dto.UpdateGoalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface GoalsService {
    GoalResponseDto create(CreateGoalDto dto, String email);
    GoalResponseDto getGoal(UUID goalId, String email);
    Page<GoalResponseDto> getGoals(String email, String search, Pageable pageable);
    GoalResponseDto update(UUID goalId, UpdateGoalDto dto, String email);
    void delete(UUID goalId, String email);
}
