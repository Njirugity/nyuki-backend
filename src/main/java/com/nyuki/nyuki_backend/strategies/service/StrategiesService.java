package com.nyuki.nyuki_backend.strategies.service;

import com.nyuki.nyuki_backend.strategies.dto.CreateStrategyDto;
import com.nyuki.nyuki_backend.strategies.dto.StrategyResponseDto;
import com.nyuki.nyuki_backend.strategies.dto.UpdateStrategyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface StrategiesService {
    StrategyResponseDto create(CreateStrategyDto dto, String email);
    StrategyResponseDto getStrategy(UUID strategyId, String email);
    Page<StrategyResponseDto> getStrategies(String email, String search, UUID goalId, Pageable pageable);
    StrategyResponseDto update(UUID strategyId, UpdateStrategyDto dto, String email);
    void delete(UUID strategyId, String email);
}
