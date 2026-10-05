package com.nyuki.nyuki_backend.strategies.service.impl;

import com.nyuki.nyuki_backend.common.exceptions.GoalNotFoundException;
import com.nyuki.nyuki_backend.common.exceptions.StrategyNotFoundException;
import com.nyuki.nyuki_backend.common.exceptions.UserNotFoundException;
import com.nyuki.nyuki_backend.goals.entity.Goal;
import com.nyuki.nyuki_backend.goals.repository.GoalsRepository;
import com.nyuki.nyuki_backend.strategies.dto.CreateStrategyDto;
import com.nyuki.nyuki_backend.strategies.dto.StrategyResponseDto;
import com.nyuki.nyuki_backend.strategies.dto.UpdateStrategyDto;
import com.nyuki.nyuki_backend.strategies.entity.Strategy;
import com.nyuki.nyuki_backend.strategies.mapper.StrategiesMapper;
import com.nyuki.nyuki_backend.strategies.repository.StrategiesRepository;
import com.nyuki.nyuki_backend.strategies.service.StrategiesService;
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
public class StrategiesServiceImpl implements StrategiesService {

    private final StrategiesRepository strategiesRepository;
    private final StrategiesMapper strategiesMapper;
    private final GoalsRepository goalsRepository;
    private final TasksRepository tasksRepository;
    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public StrategyResponseDto create(CreateStrategyDto dto, String email){
        Users owner = usersRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User not found")
        );
        Strategy strategy = strategiesMapper.toStrategy(dto);
        strategy.setGoal(findOwnedGoal(dto.goalId(), email));
        strategy.setOwner(owner);
        strategiesRepository.save(strategy);
        return strategiesMapper.toDto(strategy);
    }

    @Override
    public StrategyResponseDto getStrategy(UUID strategyId, String email){
        return strategiesMapper.toDto(findOwnedStrategy(strategyId, email));
    }

    @Override
    public Page<StrategyResponseDto> getStrategies(String email, String search, UUID goalId, Pageable pageable){
        return strategiesRepository.searchByOwner(email, search, goalId, pageable)
                .map(strategiesMapper::toDto);
    }

    @Override
    @Transactional
    public StrategyResponseDto update(UUID strategyId, UpdateStrategyDto dto, String email){
        Strategy strategy = findOwnedStrategy(strategyId, email);
        strategiesMapper.updateStrategy(dto, strategy);
        if (dto.goalId() != null && !dto.goalId().equals(strategy.getGoal().getId())) {
            Goal goal = findOwnedGoal(dto.goalId(), email);
            strategy.setGoal(goal);
            // Keep the strategy's tasks under the same goal as the strategy
            tasksRepository.updateGoalForStrategy(strategy, goal);
        }
        strategiesRepository.save(strategy);
        return strategiesMapper.toDto(strategy);
    }

    @Override
    @Transactional
    public void delete(UUID strategyId, String email){
        strategiesRepository.delete(findOwnedStrategy(strategyId, email));
    }

    private Strategy findOwnedStrategy(UUID strategyId, String email){
        return strategiesRepository.findByIdAndOwnerEmail(strategyId, email).orElseThrow(()->
                new StrategyNotFoundException("Strategy not found")
        );
    }

    private Goal findOwnedGoal(UUID goalId, String email){
        return goalsRepository.findByIdAndOwnerEmail(goalId, email).orElseThrow(()->
                new GoalNotFoundException("Goal not found")
        );
    }
}
