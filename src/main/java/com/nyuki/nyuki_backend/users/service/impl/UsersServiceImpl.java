package com.nyuki.nyuki_backend.users.service.impl;

import com.nyuki.nyuki_backend.users.mapper.UsersMapper;
import com.nyuki.nyuki_backend.users.repository.UsersRepository;
import com.nyuki.nyuki_backend.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final UsersMapper usersMapper;
}
