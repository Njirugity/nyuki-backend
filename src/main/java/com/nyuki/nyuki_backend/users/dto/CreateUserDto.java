package com.nyuki.nyuki_backend.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "User name required")
    private String userName;
    @NotBlank(message = "Email required")
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Password required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
