package com.nyuki.nyuki_backend.common.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return request.getServletPath().startsWith("/api/v1/auth/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
        try {
            String token = parseToken(request);
            if (token != null && !token.isEmpty() && jwtUtil.validateJwtToken(token)
                    && SecurityContextHolder.getContext().getAuthentication() == null){
                String email = jwtUtil.getUsernameFromToken(token);
                if(email != null){
                    var userDetails = userDetailsService.loadUserByUsername(email);
                    var authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception e){
            System.out.println("JWT authentication failed: " + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
    private String parseToken(HttpServletRequest request){
        return jwtUtil.getTokenFromHeader(request);
    }
}
