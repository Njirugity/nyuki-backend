package com.nyuki.nyuki_backend.todoitem.mapper;

import com.nyuki.nyuki_backend.todoitem.dto.CreateToDoItemDto;
import com.nyuki.nyuki_backend.todoitem.dto.ToDoItemResponseDto;
import com.nyuki.nyuki_backend.todoitem.dto.UpdateToDoItemDto;
import com.nyuki.nyuki_backend.todoitem.entity.ToDoItem;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ToDoItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "toDoList", ignore = true)
    @Mapping(target = "completed", ignore = true)
    ToDoItem toToDoItem(CreateToDoItemDto dto);

    @Mapping(source = "toDoList.id", target = "toDoListId")
    ToDoItemResponseDto toDto(ToDoItem toDoItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "toDoList", ignore = true)
    void updateToDoItem(UpdateToDoItemDto dto, @MappingTarget ToDoItem toDoItem);
}
