package com.example.eventBooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Lỗi @Valid trong Request DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {

        List<FieldErrorResponse> errors = new ArrayList<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    errors.add(
                            new FieldErrorResponse(
                                    error.getField(),
                                    error.getDefaultMessage()
                            )
                    );

                });

        ValidationErrorResponse response =
                new ValidationErrorResponse(
                        false,
                        "Validation failed",
                        errors
                );

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }


    // Lỗi Resource không tồn tại
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex) {

        ErrorResponse response =
                new ErrorResponse(
                        false,
                        ex.getMessage()
                );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }


    // Lỗi page, size, type
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationErrorResponse> handleCustomValidation(
            ValidationException ex) {

        ValidationErrorResponse response =
                new ValidationErrorResponse(
                        false,
                        "Validation failed",
                        ex.getErrors()
                );

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }
}