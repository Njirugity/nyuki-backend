package com.nyuki.nyuki_backend.common.exceptions;

import com.nyuki.nyuki_backend.common.utils.ApiMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ApiMessageResponse> userExistsException(UserExistsException e){
        ApiMessageResponse errors =  new ApiMessageResponse(e.getMessage(), HttpStatus.CONFLICT.value(), LocalDate.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiMessageResponse> userNotFoundException(UserNotFoundException e){
        ApiMessageResponse errors =  new ApiMessageResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDate.now());
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiMessageResponse> badCredentialsException(BadCredentialsException e){
        ApiMessageResponse errors =  new ApiMessageResponse("Invalid email or password", HttpStatus.UNAUTHORIZED.value(), LocalDate.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiMessageResponse> validationException(MethodArgumentNotValidException e){
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Invalid request");
        ApiMessageResponse errors =  new ApiMessageResponse(message, HttpStatus.BAD_REQUEST.value(), LocalDate.now());
        return ResponseEntity.badRequest().body(errors);
    }
}
