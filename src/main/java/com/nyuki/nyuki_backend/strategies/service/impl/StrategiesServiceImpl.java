package com.nyuki.nyuki_backend.strategies.service.impl;

import com.nyuki.nyuki_backend.strategies.mapper.StrategiesMapper;
import com.nyuki.nyuki_backend.strategies.repository.StrategiesRepository;
import com.nyuki.nyuki_backend.strategies.service.StrategiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StrategiesServiceImpl implements StrategiesService {

    private final StrategiesRepository strategiesRepository;

    private final StrategiesMapper strategiesMapper;
}
