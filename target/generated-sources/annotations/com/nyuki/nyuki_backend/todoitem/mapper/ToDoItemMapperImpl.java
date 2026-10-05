package com.nyuki.nyuki_backend.todoitem.mapper;

import com.nyuki.nyuki_backend.todoitem.dto.CreateToDoItemDto;
import com.nyuki.nyuki_backend.todoitem.dto.ToDoItemResponseDto;
import com.nyuki.nyuki_backend.todoitem.dto.UpdateToDoItemDto;
import com.nyuki.nyuki_backend.todoitem.entity.ToDoItem;
import com.nyuki.nyuki_backend.todolist.entity.ToDoList;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-05T17:34:57+0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260826-1225, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class ToDoItemMapperImpl implements ToDoItemMapper {

    @Override
    public ToDoItem toToDoItem(CreateToDoItemDto dto) {
        if ( dto == null ) {
            return null;
        }

        ToDoItem toDoItem = new ToDoItem();

        toDoItem.setDescription( dto.description() );

        return toDoItem;
    }

    @Override
    public ToDoItemResponseDto toDto(ToDoItem toDoItem) {
        if ( toDoItem == null ) {
            return null;
        }

        UUID toDoListId = null;
        UUID id = null;
        String description = null;
        boolean completed = false;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        toDoListId = toDoItemToDoListId( toDoItem );
        id = toDoItem.getId();
        description = toDoItem.getDescription();
        completed = toDoItem.isCompleted();
        createdAt = toDoItem.getCreatedAt();
        updatedAt = toDoItem.getUpdatedAt();

        ToDoItemResponseDto toDoItemResponseDto = new ToDoItemResponseDto( id, description, completed, toDoListId, createdAt, updatedAt );

        return toDoItemResponseDto;
    }

    @Override
    public void updateToDoItem(UpdateToDoItemDto dto, ToDoItem toDoItem) {
        if ( dto == null ) {
            return;
        }

        if ( dto.completed() != null ) {
            toDoItem.setCompleted( dto.completed() );
        }
        if ( dto.description() != null ) {
            toDoItem.setDescription( dto.description() );
        }
    }

    private UUID toDoItemToDoListId(ToDoItem toDoItem) {
        ToDoList toDoList = toDoItem.getToDoList();
        if ( toDoList == null ) {
            return null;
        }
        return toDoList.getId();
    }
}
