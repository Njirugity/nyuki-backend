package com.nyuki.nyuki_backend.todoitem.service;

import com.nyuki.nyuki_backend.todoitem.dto.CreateToDoItemDto;
import com.nyuki.nyuki_backend.todoitem.dto.ToDoItemResponseDto;
import com.nyuki.nyuki_backend.todoitem.dto.UpdateToDoItemDto;

import java.util.List;
import java.util.UUID;

public interface ToDoItemService {
    ToDoItemResponseDto create(UUID toDoListId, CreateToDoItemDto dto, String email);
    ToDoItemResponseDto getToDoItem(UUID toDoListId, UUID toDoItemId, String email);
    List<ToDoItemResponseDto> getToDoItems(UUID toDoListId, Boolean completed, String email);
    ToDoItemResponseDto update(UUID toDoListId, UUID toDoItemId, UpdateToDoItemDto dto, String email);
    void delete(UUID toDoListId, UUID toDoItemId, String email);
}
