package com.nyuki.nyuki_backend.strategies.controller;

import com.nyuki.nyuki_backend.strategies.dto.CreateStrategyDto;
import com.nyuki.nyuki_backend.strategies.dto.StrategyResponseDto;
import com.nyuki.nyuki_backend.strategies.dto.UpdateStrategyDto;
import com.nyuki.nyuki_backend.strategies.service.StrategiesService;
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
@RequestMapping("/api/v1/strategies")
@RequiredArgsConstructor
public class StrategiesController {

    private final StrategiesService strategiesService;

    @PostMapping
    public ResponseEntity<StrategyResponseDto> create(@Valid @RequestBody CreateStrategyDto dto,
                                                      @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.status(HttpStatus.CREATED).body(strategiesService.create(dto, user.getUsername()));
    }

    @GetMapping
    public ResponseEntity<Page<StrategyResponseDto>> getStrategies(@RequestParam(required = false) String search,
                                                                   @RequestParam(required = false) UUID goalId,
                                                                   @PageableDefault(size = 20) Pageable pageable,
                                                                   @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(strategiesService.getStrategies(user.getUsername(), search, goalId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StrategyResponseDto> getStrategy(@PathVariable UUID id,
                                                           @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(strategiesService.getStrategy(id, user.getUsername()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StrategyResponseDto> update(@PathVariable UUID id,
                                                      @Valid @RequestBody UpdateStrategyDto dto,
                                                      @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(strategiesService.update(id, dto, user.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id,
                                       @AuthenticationPrincipal UserDetails user){
        strategiesService.delete(id, user.getUsername());
        return ResponseEntity.noContent().build();
    }
}
