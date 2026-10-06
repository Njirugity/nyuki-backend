package com.nyuki.nyuki_backend.analytics.controller;

import com.nyuki.nyuki_backend.analytics.dto.ActiveAndOverdueTasksDto;
import com.nyuki.nyuki_backend.analytics.dto.DashboardResponseDto;
import com.nyuki.nyuki_backend.analytics.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final AnalyticsService analyticsService;

    @GetMapping
    public ResponseEntity<DashboardResponseDto> getDashboardResponse(@AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(analyticsService.getDashboardResponse(user.getUsername()));
    }
}
