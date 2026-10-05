package com.nyuki.nyuki_backend.todolist.mapper;

import com.nyuki.nyuki_backend.tasks.entity.Task;
import com.nyuki.nyuki_backend.todolist.dto.CreateToDoListDto;
import com.nyuki.nyuki_backend.todolist.dto.ToDoListResponseDto;
import com.nyuki.nyuki_backend.todolist.dto.UpdateToDoListDto;
import com.nyuki.nyuki_backend.todolist.entity.ToDoList;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-05T17:34:56+0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260826-1225, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class ToDoListMapperImpl implements ToDoListMapper {

    @Override
    public ToDoList toToDoList(CreateToDoListDto dto) {
        if ( dto == null ) {
            return null;
        }

        ToDoList toDoList = new ToDoList();

        toDoList.setTitle( dto.title() );

        return toDoList;
    }

    @Override
    public ToDoListResponseDto toDto(ToDoList toDoList) {
        if ( toDoList == null ) {
            return null;
        }

        UUID taskId = null;
        UUID id = null;
        String title = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        taskId = toDoListTaskId( toDoList );
        id = toDoList.getId();
        title = toDoList.getTitle();
        createdAt = toDoList.getCreatedAt();
        updatedAt = toDoList.getUpdatedAt();

        ToDoListResponseDto toDoListResponseDto = new ToDoListResponseDto( id, title, taskId, createdAt, updatedAt );

        return toDoListResponseDto;
    }

    @Override
    public void updateToDoList(UpdateToDoListDto dto, ToDoList toDoList) {
        if ( dto == null ) {
            return;
        }

        if ( dto.title() != null ) {
            toDoList.setTitle( dto.title() );
        }
    }

    private UUID toDoListTaskId(ToDoList toDoList) {
        Task task = toDoList.getTask();
        if ( task == null ) {
            return null;
        }
        return task.getId();
    }
}
