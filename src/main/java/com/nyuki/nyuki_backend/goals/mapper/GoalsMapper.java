package com.nyuki.nyuki_backend.goals.mapper;

import com.nyuki.nyuki_backend.goals.dto.CreateGoalDto;
import com.nyuki.nyuki_backend.goals.dto.GoalResponseDto;
import com.nyuki.nyuki_backend.goals.dto.UpdateGoalDto;
import com.nyuki.nyuki_backend.goals.entity.Goal;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface GoalsMapper {
    Goal toGoal(CreateGoalDto dto);
    GoalResponseDto toDto(Goal goal);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateGoal(UpdateGoalDto dto, @MappingTarget Goal goal);
}
