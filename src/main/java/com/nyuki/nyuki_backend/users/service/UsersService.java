package com.nyuki.nyuki_backend.users.service;

import com.nyuki.nyuki_backend.users.dto.CreateUserDto;
import com.nyuki.nyuki_backend.users.dto.UserListDto;

import java.util.UUID;

public interface UsersService {
    UserListDto create(CreateUserDto dto);
    UserListDto getUser(UUID userId);
    UserListDto getUserByEmail(String email);

}
