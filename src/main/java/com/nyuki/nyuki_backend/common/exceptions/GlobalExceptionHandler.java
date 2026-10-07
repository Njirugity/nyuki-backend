package com.nyuki.nyuki_backend.common.exceptions;

import com.nyuki.nyuki_backend.common.utils.ApiMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ApiMessageResponse> userExistsException(UserExistsException e){
        ApiMessageResponse errors =  new ApiMessageResponse(e.getMessage(), HttpStatus.CONFLICT.value(), Instant.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiMessageResponse> userNotFoundException(UserNotFoundException e){
        ApiMessageResponse errors =  new ApiMessageResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), Instant.now());
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(GoalNotFoundException.class)
    public ResponseEntity<ApiMessageResponse> goalNotFoundException(GoalNotFoundException e){
        ApiMessageResponse errors =  new ApiMessageResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
    @ExceptionHandler(StrategyNotFoundException.class)
    public ResponseEntity<ApiMessageResponse> strategyNotFoundException(StrategyNotFoundException e){
        ApiMessageResponse errors =  new ApiMessageResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiMessageResponse> taskNotFoundException(TaskNotFoundException e){
        ApiMessageResponse errors =  new ApiMessageResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ApiMessageResponse> scheduleNotFoundException(ScheduleNotFoundException e){
        ApiMessageResponse errors =  new ApiMessageResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
    @ExceptionHandler(FocusSessionNotFoundException.class)
    public ResponseEntity<ApiMessageResponse> focusSessionNotFoundException(FocusSessionNotFoundException e){
        ApiMessageResponse errors =  new ApiMessageResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
    @ExceptionHandler(ToDoListNotFoundException.class)
    public ResponseEntity<ApiMessageResponse> toDoListNotFoundException(ToDoListNotFoundException e){
        ApiMessageResponse errors =  new ApiMessageResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
    @ExceptionHandler(ToDoItemNotFoundException.class)
    public ResponseEntity<ApiMessageResponse> toDoItemNotFoundException(ToDoItemNotFoundException e){
        ApiMessageResponse errors =  new ApiMessageResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiMessageResponse> invalidRequestException(InvalidRequestException e){
        ApiMessageResponse errors =  new ApiMessageResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), Instant.now());
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiMessageResponse> badCredentialsException(BadCredentialsException e){
        ApiMessageResponse errors =  new ApiMessageResponse("Invalid email or password", HttpStatus.UNAUTHORIZED.value(), Instant.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiMessageResponse> validationException(MethodArgumentNotValidException e){
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Invalid request");
        ApiMessageResponse errors =  new ApiMessageResponse(message, HttpStatus.BAD_REQUEST.value(), Instant.now());
        return ResponseEntity.badRequest().body(errors);
    }
}
