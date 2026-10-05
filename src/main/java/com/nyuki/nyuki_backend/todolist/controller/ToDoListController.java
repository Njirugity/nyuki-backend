package com.nyuki.nyuki_backend.todolist.controller;

import com.nyuki.nyuki_backend.todolist.dto.CreateToDoListDto;
import com.nyuki.nyuki_backend.todolist.dto.ToDoListResponseDto;
import com.nyuki.nyuki_backend.todolist.dto.UpdateToDoListDto;
import com.nyuki.nyuki_backend.todolist.service.ToDoListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todo-lists")
@RequiredArgsConstructor
public class ToDoListController {

    private final ToDoListService toDoListService;

    @PostMapping
    public ResponseEntity<ToDoListResponseDto> create(@Valid @RequestBody CreateToDoListDto dto,
                                                      @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoListService.create(dto, user.getUsername()));
    }

    @GetMapping
    public ResponseEntity<Page<ToDoListResponseDto>> getToDoLists(@RequestParam(required = false) String search,
                                                                  @RequestParam(required = false) UUID taskId,
                                                                  @PageableDefault(size = 20) Pageable pageable,
                                                                  @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(toDoListService.getToDoLists(user.getUsername(), search, taskId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoListResponseDto> getToDoList(@PathVariable UUID id,
                                                           @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(toDoListService.getToDoList(id, user.getUsername()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ToDoListResponseDto> update(@PathVariable UUID id,
                                                      @Valid @RequestBody UpdateToDoListDto dto,
                                                      @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(toDoListService.update(id, dto, user.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id,
                                       @AuthenticationPrincipal UserDetails user){
        toDoListService.delete(id, user.getUsername());
        return ResponseEntity.noContent().build();
    }
}
