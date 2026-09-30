package com.nyuki.nyuki_backend.auth.service.impl;

import com.nyuki.nyuki_backend.auth.dto.AuthResponseDto;
import com.nyuki.nyuki_backend.auth.dto.LoginRequestDto;
import com.nyuki.nyuki_backend.auth.service.AuthService;
import com.nyuki.nyuki_backend.common.jwt.JwtUtil;
import com.nyuki.nyuki_backend.users.dto.CreateUserDto;
import com.nyuki.nyuki_backend.users.dto.UserListDto;
import com.nyuki.nyuki_backend.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponseDto register(CreateUserDto dto){
        UserListDto user = usersService.create(dto);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.email());
        return buildResponse(userDetails, user);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto dto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return buildResponse(userDetails, usersService.getUserByEmail(userDetails.getUsername()));
    }

    private AuthResponseDto buildResponse(UserDetails userDetails, UserListDto user){
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponseDto(token, user);
    }
}
