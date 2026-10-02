package com.nyuki.nyuki_backend.goals.service.impl;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.common.exceptions.GoalNotFoundException;
import com.nyuki.nyuki_backend.common.exceptions.InvalidRequestException;
import com.nyuki.nyuki_backend.common.exceptions.UserNotFoundException;
import com.nyuki.nyuki_backend.goals.dto.CreateGoalDto;
import com.nyuki.nyuki_backend.goals.dto.GoalResponseDto;
import com.nyuki.nyuki_backend.goals.dto.UpdateGoalDto;
import com.nyuki.nyuki_backend.goals.entity.Goal;
import com.nyuki.nyuki_backend.goals.mapper.GoalsMapper;
import com.nyuki.nyuki_backend.goals.repository.GoalsRepository;
import com.nyuki.nyuki_backend.goals.service.GoalsService;
import com.nyuki.nyuki_backend.strategies.repository.StrategiesRepository;
import com.nyuki.nyuki_backend.tasks.repository.TasksRepository;
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
public class GoalsServiceImpl implements GoalsService {

    private final GoalsRepository goalsRepository;
    private final GoalsMapper goalsMapper;
    private final UsersRepository usersRepository;
    private final StrategiesRepository strategiesRepository;
    private final TasksRepository tasksRepository;

    @Override
    @Transactional
    public GoalResponseDto create(CreateGoalDto dto, String email){
        Users owner = usersRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User not found")
        );
        Goal goal = goalsMapper.toGoal(dto);
        if (goal.getStatus() == null) {
            goal.setStatus(ProgressStatus.NOT_STARTED);
        }
        validateDates(goal);
        goal.setOwner(owner);
        goalsRepository.save(goal);
        return goalsMapper.toDto(goal);
    }

    @Override
    public GoalResponseDto getGoal(UUID goalId, String email){
        return goalsMapper.toDto(findOwnedGoal(goalId, email));
    }

    @Override
    public Page<GoalResponseDto> getGoals(String email, String search, Pageable pageable) {

        return goalsRepository.searchByOwner(email, search, pageable)
                .map(goalsMapper::toDto);
    }

    @Override
    @Transactional
    public GoalResponseDto update(UUID goalId, UpdateGoalDto dto, String email){
        Goal goal = findOwnedGoal(goalId, email);
        goalsMapper.updateGoal(dto, goal);
        validateDates(goal);
        goalsRepository.save(goal);
        return goalsMapper.toDto(goal);
    }

    @Override
    @Transactional
    public void delete(UUID goalId, String email){
        Goal goal = findOwnedGoal(goalId, email);
        tasksRepository.deleteByGoal(goal);
        strategiesRepository.deleteByGoal(goal);
        goalsRepository.delete(goal);
    }

    private Goal findOwnedGoal(UUID goalId, String email){
        return goalsRepository.findByIdAndOwnerEmail(goalId, email).orElseThrow(()->
                new GoalNotFoundException("Goal not found")
        );
    }

    private void validateDates(Goal goal){
        if (goal.getStartDate() != null && goal.getEndDate() != null
                && goal.getEndDate().isBefore(goal.getStartDate())) {
            throw new InvalidRequestException("End date cannot be before start date");
        }
    }
}
