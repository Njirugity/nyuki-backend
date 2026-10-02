package com.nyuki.nyuki_backend.tasks.mapper;

import com.nyuki.nyuki_backend.tasks.dto.CreateTaskDto;
import com.nyuki.nyuki_backend.tasks.dto.TaskResponseDto;
import com.nyuki.nyuki_backend.tasks.dto.UpdateTaskDto;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TasksMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "goal", ignore = true)
    @Mapping(target = "strategy", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    Task toTask(CreateTaskDto dto);

    @Mapping(source = "goal.id", target = "goalId")
    @Mapping(source = "strategy.id", target = "strategyId")
    TaskResponseDto toDto(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "goal", ignore = true)
    @Mapping(target = "strategy", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    void updateTask(UpdateTaskDto dto, @MappingTarget Task task);
}
