package com.nyuki.nyuki_backend.tasks.controller;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.tasks.dto.CreateTaskDto;
import com.nyuki.nyuki_backend.tasks.dto.TaskResponseDto;
import com.nyuki.nyuki_backend.tasks.dto.UpdateTaskDto;
import com.nyuki.nyuki_backend.tasks.service.TasksService;
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
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    public ResponseEntity<TaskResponseDto> create(@Valid @RequestBody CreateTaskDto dto,
                                                  @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.status(HttpStatus.CREATED).body(tasksService.create(dto, user.getUsername()));
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponseDto>> getTasks(@RequestParam(required = false) String search,
                                                          @RequestParam(required = false) UUID goalId,
                                                          @RequestParam(required = false) UUID strategyId,
                                                          @RequestParam(required = false) ProgressStatus status,
                                                          @PageableDefault(size = 20) Pageable pageable,
                                                          @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(tasksService.getTasks(user.getUsername(), search, goalId, strategyId, status, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable UUID id,
                                                   @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(tasksService.getTask(id, user.getUsername()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDto> update(@PathVariable UUID id,
                                                  @Valid @RequestBody UpdateTaskDto dto,
                                                  @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(tasksService.update(id, dto, user.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id,
                                       @AuthenticationPrincipal UserDetails user){
        tasksService.delete(id, user.getUsername());
        return ResponseEntity.noContent().build();
    }
}
