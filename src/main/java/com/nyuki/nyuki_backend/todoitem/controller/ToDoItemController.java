package com.nyuki.nyuki_backend.todoitem.controller;

import com.nyuki.nyuki_backend.todoitem.dto.CreateToDoItemDto;
import com.nyuki.nyuki_backend.todoitem.dto.ToDoItemResponseDto;
import com.nyuki.nyuki_backend.todoitem.dto.UpdateToDoItemDto;
import com.nyuki.nyuki_backend.todoitem.service.ToDoItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todo-lists/{listId}/items")
@RequiredArgsConstructor
public class ToDoItemController {

    private final ToDoItemService toDoItemService;

    @PostMapping
    public ResponseEntity<ToDoItemResponseDto> create(@PathVariable UUID listId,
                                                      @Valid @RequestBody CreateToDoItemDto dto,
                                                      @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoItemService.create(listId, dto, user.getUsername()));
    }

    @GetMapping
    public ResponseEntity<List<ToDoItemResponseDto>> getToDoItems(@PathVariable UUID listId,
                                                                  @RequestParam(required = false) Boolean completed,
                                                                  @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(toDoItemService.getToDoItems(listId, completed, user.getUsername()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoItemResponseDto> getToDoItem(@PathVariable UUID listId,
                                                           @PathVariable UUID id,
                                                           @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(toDoItemService.getToDoItem(listId, id, user.getUsername()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ToDoItemResponseDto> update(@PathVariable UUID listId,
                                                      @PathVariable UUID id,
                                                      @Valid @RequestBody UpdateToDoItemDto dto,
                                                      @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(toDoItemService.update(listId, id, dto, user.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID listId,
                                       @PathVariable UUID id,
                                       @AuthenticationPrincipal UserDetails user){
        toDoItemService.delete(listId, id, user.getUsername());
        return ResponseEntity.noContent().build();
    }
}
