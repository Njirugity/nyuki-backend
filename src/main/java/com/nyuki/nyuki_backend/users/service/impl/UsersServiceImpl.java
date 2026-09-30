package com.nyuki.nyuki_backend.users.service.impl;

import com.nyuki.nyuki_backend.common.exceptions.UserExistsException;
import com.nyuki.nyuki_backend.common.exceptions.UserNotFoundException;
import com.nyuki.nyuki_backend.users.dto.CreateUserDto;
import com.nyuki.nyuki_backend.users.dto.UserListDto;
import com.nyuki.nyuki_backend.users.entity.Users;
import com.nyuki.nyuki_backend.users.mapper.UsersMapper;
import com.nyuki.nyuki_backend.users.repository.UsersRepository;
import com.nyuki.nyuki_backend.users.service.UsersService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserListDto create(CreateUserDto dto){
        if (usersRepository.existsByEmail(dto.getEmail())) {
            throw new UserExistsException("User with this email already exists");
        }
        if (usersRepository.existsByUserName(dto.getUserName())) {
            throw new UserExistsException("User name is already taken");
        }
        Users user = usersMapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        usersRepository.save(user);
        return usersMapper.toDto(user);
    }
    @Override
    public UserListDto getUser(UUID userId){
        return usersMapper.toDto(usersRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("User not found")
        ));
    }
    @Override
    public UserListDto getUserByEmail(String email){
        return usersMapper.toDto(usersRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User not found")
        ));
    }
}
