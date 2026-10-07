package com.nyuki.nyuki_backend.tasks.service.impl;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.common.exceptions.GoalNotFoundException;
import com.nyuki.nyuki_backend.common.exceptions.InvalidRequestException;
import com.nyuki.nyuki_backend.common.exceptions.StrategyNotFoundException;
import com.nyuki.nyuki_backend.common.exceptions.TaskNotFoundException;
import com.nyuki.nyuki_backend.common.exceptions.UserNotFoundException;
import com.nyuki.nyuki_backend.goals.entity.Goal;
import com.nyuki.nyuki_backend.goals.repository.GoalsRepository;
import com.nyuki.nyuki_backend.strategies.entity.Strategy;
import com.nyuki.nyuki_backend.strategies.repository.StrategiesRepository;
import com.nyuki.nyuki_backend.tasks.dto.CreateTaskDto;
import com.nyuki.nyuki_backend.tasks.dto.TaskResponseDto;
import com.nyuki.nyuki_backend.tasks.dto.UpdateTaskDto;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import com.nyuki.nyuki_backend.tasks.mapper.TasksMapper;
import com.nyuki.nyuki_backend.tasks.repository.TasksRepository;
import com.nyuki.nyuki_backend.tasks.service.TasksService;
import com.nyuki.nyuki_backend.users.entity.Users;
import com.nyuki.nyuki_backend.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {

    private final TasksRepository tasksRepository;
    private final TasksMapper tasksMapper;
    private final GoalsRepository goalsRepository;
    private final StrategiesRepository strategiesRepository;
    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public TaskResponseDto create(CreateTaskDto dto, String email){
        Users owner = usersRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User not found")
        );
        Task task = tasksMapper.toTask(dto);
        if (task.getStatus() == null) {
            task.setStatus(ProgressStatus.NOT_STARTED);
        }
        Goal goal = dto.goalId() == null ? null : findOwnedGoal(dto.goalId(), email);
        Strategy strategy = dto.strategyId() == null ? null : findOwnedStrategy(dto.strategyId(), email);
        applyLinks(task, goal, strategy);
        updateCompletedAt(task, null);
        validateDates(task);
        task.setOwner(owner);
        tasksRepository.save(task);
        return tasksMapper.toDto(task);
    }

    @Override
    public TaskResponseDto getTask(UUID taskId, String email){
        return tasksMapper.toDto(findOwnedTask(taskId, email));
    }

    @Override
    public Page<TaskResponseDto> getTasks(String email, String search, UUID goalId, UUID strategyId,
                                          ProgressStatus status, Pageable pageable){
        return tasksRepository.searchByOwner(email, search, goalId, strategyId, status, pageable)
                .map(tasksMapper::toDto);
    }

    @Override
    @Transactional
    public TaskResponseDto update(UUID taskId, UpdateTaskDto dto, String email){
        Task task = findOwnedTask(taskId, email);
        ProgressStatus previousStatus = task.getStatus();
        tasksMapper.updateTask(dto, task);

        Strategy strategy = dto.strategyId() == null ? task.getStrategy() : findOwnedStrategy(dto.strategyId(), email);
        Goal goal;
        if (dto.goalId() != null) {
            goal = findOwnedGoal(dto.goalId(), email);
        } else if (dto.strategyId() != null) {
            // Moving to a new strategy also moves the task to that strategy's goal
            goal = null;
        } else {
            goal = task.getGoal();
        }
        applyLinks(task, goal, strategy);
        updateCompletedAt(task, previousStatus);
        validateDates(task);
        tasksRepository.save(task);
        return tasksMapper.toDto(task);
    }

    @Override
    @Transactional
    public void delete(UUID taskId, String email){
        tasksRepository.delete(findOwnedTask(taskId, email));
    }

    private void applyLinks(Task task, Goal goal, Strategy strategy){
        if (strategy != null) {
            if (goal == null) {
                goal = strategy.getGoal();
            } else if (!goal.getId().equals(strategy.getGoal().getId())) {
                throw new InvalidRequestException("Strategy does not belong to this goal");
            }
        }
        task.setGoal(goal);
        task.setStrategy(strategy);
    }

    private void updateCompletedAt(Task task, ProgressStatus previousStatus){
        if (task.getStatus() != ProgressStatus.COMPLETED) {
            task.setCompletedAt(null);
        } else if (previousStatus != ProgressStatus.COMPLETED) {
            task.setCompletedAt(Instant.now());
        }
    }

    @Override
    public Task findOwnedTask(UUID taskId, String email){
        return tasksRepository.findByIdAndOwnerEmail(taskId, email).orElseThrow(()->
                new TaskNotFoundException("Task not found")
        );
    }

    private Goal findOwnedGoal(UUID goalId, String email){
        return goalsRepository.findByIdAndOwnerEmail(goalId, email).orElseThrow(()->
                new GoalNotFoundException("Goal not found")
        );
    }

    private Strategy findOwnedStrategy(UUID strategyId, String email){
        return strategiesRepository.findByIdAndOwnerEmail(strategyId, email).orElseThrow(()->
                new StrategyNotFoundException("Strategy not found")
        );
    }

    private void validateDates(Task task){
        if (task.getStartDate() != null && task.getDueDate() != null
                && task.getDueDate().isBefore(task.getStartDate())) {
            throw new InvalidRequestException("Due date cannot be before start date");
        }
    }
}
