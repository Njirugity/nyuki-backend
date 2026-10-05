package com.nyuki.nyuki_backend.schedule.controller;

import com.nyuki.nyuki_backend.schedule.dto.CreateScheduleDto;
import com.nyuki.nyuki_backend.schedule.dto.ScheduleResponseDto;
import com.nyuki.nyuki_backend.schedule.dto.UpdateScheduleDto;
import com.nyuki.nyuki_backend.schedule.service.ScheduleService;
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
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/{taskId}")
    public ResponseEntity<ScheduleResponseDto> create(@Valid @RequestBody CreateScheduleDto dto,
                                                      @PathVariable  UUID taskId,
                                                      @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.create(taskId, dto, user.getUsername()));
    }
    @GetMapping
    public ResponseEntity<Page<ScheduleResponseDto>> listSchedule(@RequestParam(required = false) UUID taskId,
                                                                  @PageableDefault(size=20)Pageable pageable,
                                                                  @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.listSchedules(user.getUsername(), taskId,
                pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable UUID id,
                                                           @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(scheduleService.getSchedule(id, user.getUsername()));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable UUID id,
                                                              @RequestBody UpdateScheduleDto dto,
                                                              @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(scheduleService.updateSchedule(id, dto, user.getUsername()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable UUID id,
                                            @AuthenticationPrincipal UserDetails user){
        scheduleService.deleteSchedule(id, user.getUsername());
        return ResponseEntity.noContent().build();
    }
}
