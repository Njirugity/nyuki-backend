package com.nyuki.nyuki_backend.focussession.controller;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.focussession.dto.CreateFocusSessionDto;
import com.nyuki.nyuki_backend.focussession.dto.FocusSessionResponseDto;
import com.nyuki.nyuki_backend.focussession.dto.UpdateFocusSessionDto;
import com.nyuki.nyuki_backend.focussession.service.FocusSessionService;
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
@RequestMapping("/api/v1/focus-sessions")
@RequiredArgsConstructor
public class FocusSessionController {

    private final FocusSessionService focusSessionService;

    @PostMapping
    public ResponseEntity<FocusSessionResponseDto> create(@Valid @RequestBody CreateFocusSessionDto dto,
                                                          @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.status(HttpStatus.CREATED).body(focusSessionService.create(dto, user.getUsername()));
    }

    @GetMapping
    public ResponseEntity<Page<FocusSessionResponseDto>> getFocusSessions(@RequestParam(required = false) UUID scheduleId,
                                                                          @RequestParam(required = false) UUID taskId,
                                                                          @RequestParam(required = false) ProgressStatus status,
                                                                          @PageableDefault(size = 20) Pageable pageable,
                                                                          @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(focusSessionService.getFocusSessions(user.getUsername(), scheduleId, taskId, status, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FocusSessionResponseDto> getFocusSession(@PathVariable UUID id,
                                                                   @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(focusSessionService.getFocusSession(id, user.getUsername()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FocusSessionResponseDto> update(@PathVariable UUID id,
                                                          @Valid @RequestBody UpdateFocusSessionDto dto,
                                                          @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(focusSessionService.update(id, dto, user.getUsername()));
    }

    @PostMapping("/{id}/pause")
    public ResponseEntity<FocusSessionResponseDto> pause(@PathVariable UUID id,
                                                         @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(focusSessionService.pause(id, user.getUsername()));
    }

    @PostMapping("/{id}/resume")
    public ResponseEntity<FocusSessionResponseDto> resume(@PathVariable UUID id,
                                                          @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(focusSessionService.resume(id, user.getUsername()));
    }

    @PostMapping("/{id}/stop")
    public ResponseEntity<FocusSessionResponseDto> stop(@PathVariable UUID id,
                                                        @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(focusSessionService.stop(id, user.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id,
                                       @AuthenticationPrincipal UserDetails user){
        focusSessionService.delete(id, user.getUsername());
        return ResponseEntity.noContent().build();
    }
}
