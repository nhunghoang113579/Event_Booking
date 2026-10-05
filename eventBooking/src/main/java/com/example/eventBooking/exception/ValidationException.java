package com.example.eventBooking.exception;

import com.example.eventBooking.dto.response.FieldError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ValidationException extends BusinessException {

    private final List<FieldError> errors;

    public ValidationException(List<FieldError> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}