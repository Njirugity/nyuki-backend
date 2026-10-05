package com.nyuki.nyuki_backend.todolist.service.impl;

import com.nyuki.nyuki_backend.common.exceptions.TaskNotFoundException;
import com.nyuki.nyuki_backend.common.exceptions.ToDoListNotFoundException;
import com.nyuki.nyuki_backend.common.exceptions.UserNotFoundException;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import com.nyuki.nyuki_backend.tasks.repository.TasksRepository;
import com.nyuki.nyuki_backend.todolist.dto.CreateToDoListDto;
import com.nyuki.nyuki_backend.todolist.dto.ToDoListResponseDto;
import com.nyuki.nyuki_backend.todolist.dto.UpdateToDoListDto;
import com.nyuki.nyuki_backend.todolist.entity.ToDoList;
import com.nyuki.nyuki_backend.todolist.mapper.ToDoListMapper;
import com.nyuki.nyuki_backend.todolist.repository.ToDoListRepository;
import com.nyuki.nyuki_backend.todolist.service.ToDoListService;
import com.nyuki.nyuki_backend.users.entity.Users;
import com.nyuki.nyuki_backend.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ToDoListServiceImpl implements ToDoListService {

    private final ToDoListRepository toDoListRepository;
    private final ToDoListMapper toDoListMapper;
    private final TasksRepository tasksRepository;
    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public ToDoListResponseDto create(CreateToDoListDto dto, String email){
        Users owner = usersRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User not found")
        );
        ToDoList toDoList = toDoListMapper.toToDoList(dto);
        if (dto.taskId() != null) {
            toDoList.setTask(findOwnedTask(dto.taskId(), email));
        }
        toDoList.setOwner(owner);
        toDoListRepository.save(toDoList);
        return toDoListMapper.toDto(toDoList);
    }

    @Override
    public ToDoListResponseDto getToDoList(UUID toDoListId, String email){
        return toDoListMapper.toDto(findOwnedToDoList(toDoListId, email));
    }

    @Override
    public Page<ToDoListResponseDto> getToDoLists(String email, String search, UUID taskId, Pageable pageable){
        return toDoListRepository.searchByOwner(email, search, taskId, pageable)
                .map(toDoListMapper::toDto);
    }

    @Override
    @Transactional
    public ToDoListResponseDto update(UUID toDoListId, UpdateToDoListDto dto, String email){
        ToDoList toDoList = findOwnedToDoList(toDoListId, email);
        toDoListMapper.updateToDoList(dto, toDoList);
        if (dto.taskId() != null) {
            toDoList.setTask(findOwnedTask(dto.taskId(), email));
        }
        toDoListRepository.save(toDoList);
        return toDoListMapper.toDto(toDoList);
    }

    @Override
    @Transactional
    public void delete(UUID toDoListId, String email){
        toDoListRepository.delete(findOwnedToDoList(toDoListId, email));
    }

    @Override
    public ToDoList findOwnedToDoList(UUID toDoListId, String email){
        return toDoListRepository.findByIdAndOwnerEmail(toDoListId, email).orElseThrow(()->
                new ToDoListNotFoundException("To-do list not found")
        );
    }

    private Task findOwnedTask(UUID taskId, String email){
        return tasksRepository.findByIdAndOwnerEmail(taskId, email).orElseThrow(()->
                new TaskNotFoundException("Task not found")
        );
    }
}
