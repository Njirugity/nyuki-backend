package com.nyuki.nyuki_backend.auth.service.impl;

import com.nyuki.nyuki_backend.auth.mapper.AuthMapper;
import com.nyuki.nyuki_backend.auth.repository.AuthRepository;
import com.nyuki.nyuki_backend.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    private final AuthMapper authMapper;
}
