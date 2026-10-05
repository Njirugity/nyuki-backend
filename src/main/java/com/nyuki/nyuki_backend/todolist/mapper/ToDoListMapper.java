package com.nyuki.nyuki_backend.todolist.mapper;

import com.nyuki.nyuki_backend.todolist.dto.CreateToDoListDto;
import com.nyuki.nyuki_backend.todolist.dto.ToDoListResponseDto;
import com.nyuki.nyuki_backend.todolist.dto.UpdateToDoListDto;
import com.nyuki.nyuki_backend.todolist.entity.ToDoList;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ToDoListMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "task", ignore = true)
    ToDoList toToDoList(CreateToDoListDto dto);

    @Mapping(source = "task.id", target = "taskId")
    ToDoListResponseDto toDto(ToDoList toDoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "task", ignore = true)
    void updateToDoList(UpdateToDoListDto dto, @MappingTarget ToDoList toDoList);
}
