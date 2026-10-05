package com.nyuki.nyuki_backend.todoitem.service.impl;

import com.nyuki.nyuki_backend.common.exceptions.ToDoItemNotFoundException;
import com.nyuki.nyuki_backend.todoitem.dto.CreateToDoItemDto;
import com.nyuki.nyuki_backend.todoitem.dto.ToDoItemResponseDto;
import com.nyuki.nyuki_backend.todoitem.dto.UpdateToDoItemDto;
import com.nyuki.nyuki_backend.todoitem.entity.ToDoItem;
import com.nyuki.nyuki_backend.todoitem.mapper.ToDoItemMapper;
import com.nyuki.nyuki_backend.todoitem.repository.ToDoItemRepository;
import com.nyuki.nyuki_backend.todoitem.service.ToDoItemService;
import com.nyuki.nyuki_backend.todolist.entity.ToDoList;
import com.nyuki.nyuki_backend.todolist.service.ToDoListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// Items have no owner of their own; access is checked through the list's owner
@Service
@RequiredArgsConstructor
public class ToDoItemServiceImpl implements ToDoItemService {

    private final ToDoItemRepository toDoItemRepository;
    private final ToDoItemMapper toDoItemMapper;
    private final ToDoListService toDoListService;

    @Override
    @Transactional
    public ToDoItemResponseDto create(UUID toDoListId, CreateToDoItemDto dto, String email){
        ToDoList toDoList = toDoListService.findOwnedToDoList(toDoListId, email);
        ToDoItem toDoItem = toDoItemMapper.toToDoItem(dto);
        toDoItem.setCompleted(Boolean.TRUE.equals(dto.completed()));
        toDoItem.setToDoList(toDoList);
        toDoItemRepository.save(toDoItem);
        return toDoItemMapper.toDto(toDoItem);
    }

    @Override
    public ToDoItemResponseDto getToDoItem(UUID toDoListId, UUID toDoItemId, String email){
        return toDoItemMapper.toDto(findOwnedToDoItem(toDoListId, toDoItemId, email));
    }

    @Override
    public List<ToDoItemResponseDto> getToDoItems(UUID toDoListId, Boolean completed, String email){
        ToDoList toDoList = toDoListService.findOwnedToDoList(toDoListId, email);
        List<ToDoItem> items = completed == null
                ? toDoItemRepository.findByToDoListOrderByCreatedAtAsc(toDoList)
                : toDoItemRepository.findByToDoListAndCompletedOrderByCreatedAtAsc(toDoList, completed);
        return items.stream().map(toDoItemMapper::toDto).toList();
    }

    @Override
    @Transactional
    public ToDoItemResponseDto update(UUID toDoListId, UUID toDoItemId, UpdateToDoItemDto dto, String email){
        ToDoItem toDoItem = findOwnedToDoItem(toDoListId, toDoItemId, email);
        toDoItemMapper.updateToDoItem(dto, toDoItem);
        toDoItemRepository.save(toDoItem);
        return toDoItemMapper.toDto(toDoItem);
    }

    @Override
    @Transactional
    public void delete(UUID toDoListId, UUID toDoItemId, String email){
        toDoItemRepository.delete(findOwnedToDoItem(toDoListId, toDoItemId, email));
    }

    private ToDoItem findOwnedToDoItem(UUID toDoListId, UUID toDoItemId, String email){
        ToDoList toDoList = toDoListService.findOwnedToDoList(toDoListId, email);
        return toDoItemRepository.findByIdAndToDoList(toDoItemId, toDoList).orElseThrow(()->
                new ToDoItemNotFoundException("To-do item not found")
        );
    }
}
