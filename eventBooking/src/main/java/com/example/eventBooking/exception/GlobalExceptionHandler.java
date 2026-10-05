package com.example.eventBooking.exception;

import com.example.eventBooking.dto.response.ApiResponse;
import com.example.eventBooking.dto.response.ErrorResponse;
import com.example.eventBooking.dto.response.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Lỗi @Valid trong Request DTO   // Lỗi page, size, type
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {

        List<FieldError> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new FieldError(
                        error.getField(),
                        error.getDefaultMessage()))
                .toList();

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .message("Validation failed")
                .errors(errors)
                .build();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }


//
//
//    //Lỗi Resource không tồn tại

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException ex) {

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return ResponseEntity
                .status(ex.getStatus())
                .body(response);
    }

    // Custom ValidationException
    // page / size / type / nearby
    // ==========================================
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            ValidationException ex) {

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .errors(ex.getErrors())
                .build();

        return ResponseEntity
                .status(ex.getStatus())
                .body(response);
    }
    }


