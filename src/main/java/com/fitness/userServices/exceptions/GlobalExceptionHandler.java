package com.fitness.userServices.exceptions;

import com.fitness.userServices.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyRgisterUserException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyRegisterUser(AlreadyRgisterUserException e){
        return new ResponseEntity<>(ErrorResponse.errorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "User Already Exists",
                e.getMessage(),
                "/api/user/register"
        ),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e){
        return new ResponseEntity<>(ErrorResponse.errorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "User Does not Exists",
                e.getMessage(),
                "/api/user"
        ),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return new ResponseEntity<>(ErrorResponse.errorResponseWithFields(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                "Validation Failed",
                "Invalid Field",
                null,
                errors
        ),HttpStatus.UNAUTHORIZED);
    }

}
