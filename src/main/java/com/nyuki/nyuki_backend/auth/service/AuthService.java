package com.nyuki.nyuki_backend.auth.service;

import com.nyuki.nyuki_backend.auth.dto.AuthResponseDto;
import com.nyuki.nyuki_backend.auth.dto.LoginRequestDto;
import com.nyuki.nyuki_backend.users.dto.CreateUserDto;

public interface AuthService {
    AuthResponseDto register(CreateUserDto dto);
    AuthResponseDto login(LoginRequestDto dto);
}
