package com.nyuki.nyuki_backend.auth.dto;

import com.nyuki.nyuki_backend.users.dto.UserListDto;

public record AuthResponseDto(
        String token,
        UserListDto user
) {
}
