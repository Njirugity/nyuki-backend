package com.nyuki.nyuki_backend.users.mapper;

import com.nyuki.nyuki_backend.users.dto.CreateUserDto;
import com.nyuki.nyuki_backend.users.dto.UserListDto;
import com.nyuki.nyuki_backend.users.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    Users toUser(CreateUserDto dto);
    UserListDto toDto(Users users);
}
