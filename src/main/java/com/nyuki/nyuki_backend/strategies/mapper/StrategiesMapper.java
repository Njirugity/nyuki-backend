package com.nyuki.nyuki_backend.strategies.mapper;

import com.nyuki.nyuki_backend.strategies.dto.CreateStrategyDto;
import com.nyuki.nyuki_backend.strategies.dto.StrategyResponseDto;
import com.nyuki.nyuki_backend.strategies.dto.UpdateStrategyDto;
import com.nyuki.nyuki_backend.strategies.entity.Strategy;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StrategiesMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "goal", ignore = true)
    Strategy toStrategy(CreateStrategyDto dto);

    @Mapping(source = "goal.id", target = "goalId")
    @Mapping(source = "goal.title", target = "goalTitle")
    StrategyResponseDto toDto(Strategy strategy);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "goal", ignore = true)
    void updateStrategy(UpdateStrategyDto dto, @MappingTarget Strategy strategy);
}
