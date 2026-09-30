package com.nyuki.nyuki_backend.auth.controller;

import com.nyuki.nyuki_backend.auth.dto.AuthResponseDto;
import com.nyuki.nyuki_backend.auth.dto.LoginRequestDto;
import com.nyuki.nyuki_backend.auth.service.AuthService;
import com.nyuki.nyuki_backend.users.dto.CreateUserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody CreateUserDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }
}
