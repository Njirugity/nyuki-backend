package com.nyuki.nyuki_backend.users.mapper;

import com.nyuki.nyuki_backend.users.dto.CreateUserDto;
import com.nyuki.nyuki_backend.users.dto.UserListDto;
import com.nyuki.nyuki_backend.users.entity.Users;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-30T22:29:29+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Ubuntu)"
)
@Component
public class UsersMapperImpl implements UsersMapper {

    @Override
    public Users toUser(CreateUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        Users users = new Users();

        users.setUserName( dto.getUserName() );
        users.setEmail( dto.getEmail() );
        users.setPassword( dto.getPassword() );

        return users;
    }

    @Override
    public UserListDto toDto(Users users) {
        if ( users == null ) {
            return null;
        }

        String userName = null;
        String email = null;

        userName = users.getUserName();
        email = users.getEmail();

        UserListDto userListDto = new UserListDto( userName, email );

        return userListDto;
    }
}
