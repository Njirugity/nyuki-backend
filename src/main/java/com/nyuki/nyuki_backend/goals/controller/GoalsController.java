package com.nyuki.nyuki_backend.goals.controller;

import com.nyuki.nyuki_backend.common.utils.PageResponse;
import com.nyuki.nyuki_backend.goals.dto.CreateGoalDto;
import com.nyuki.nyuki_backend.goals.dto.GoalResponseDto;
import com.nyuki.nyuki_backend.goals.dto.UpdateGoalDto;
import com.nyuki.nyuki_backend.goals.service.GoalsService;
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
@RequestMapping("/api/v1/goals")
@RequiredArgsConstructor
public class GoalsController {

    private final GoalsService goalsService;

    @PostMapping
    public ResponseEntity<GoalResponseDto> create(@Valid @RequestBody CreateGoalDto dto,
                                                  @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.status(HttpStatus.CREATED).body(goalsService.create(dto, user.getUsername()));
    }

    @GetMapping
    public ResponseEntity<Page<GoalResponseDto>> getGoals(@RequestParam(required = false) String search,
                                                          @PageableDefault(size = 20) Pageable pageable,
                                                          @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(goalsService.getGoals(user.getUsername(), search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalResponseDto> getGoal(@PathVariable UUID id,
                                                   @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(goalsService.getGoal(id, user.getUsername()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GoalResponseDto> update(@PathVariable UUID id,
                                                  @Valid @RequestBody UpdateGoalDto dto,
                                                  @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(goalsService.update(id, dto, user.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id,
                                       @AuthenticationPrincipal UserDetails user){
        goalsService.delete(id, user.getUsername());
        return ResponseEntity.noContent().build();
    }
}
