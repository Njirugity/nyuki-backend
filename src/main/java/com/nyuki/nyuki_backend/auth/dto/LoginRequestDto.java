package com.nyuki.nyuki_backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank(message = "Email required")
        @Email(message = "Email is not valid")
        String email,
        @NotBlank(message = "Password required")
        String password
) {
}
