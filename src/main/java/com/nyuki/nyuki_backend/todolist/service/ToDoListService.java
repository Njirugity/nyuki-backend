package com.nyuki.nyuki_backend.todolist.service;

import com.nyuki.nyuki_backend.todolist.dto.CreateToDoListDto;
import com.nyuki.nyuki_backend.todolist.dto.ToDoListResponseDto;
import com.nyuki.nyuki_backend.todolist.dto.UpdateToDoListDto;
import com.nyuki.nyuki_backend.todolist.entity.ToDoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ToDoListService {
    ToDoListResponseDto create(CreateToDoListDto dto, String email);
    ToDoListResponseDto getToDoList(UUID toDoListId, String email);
    Page<ToDoListResponseDto> getToDoLists(String email, String search, UUID taskId, Pageable pageable);
    ToDoListResponseDto update(UUID toDoListId, UpdateToDoListDto dto, String email);
    void delete(UUID toDoListId, String email);
    ToDoList findOwnedToDoList(UUID toDoListId, String email);
}
