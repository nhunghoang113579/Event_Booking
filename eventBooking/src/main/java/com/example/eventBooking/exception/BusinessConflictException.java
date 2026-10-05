package com.example.eventBooking.exception;

import org.springframework.http.HttpStatus;

public class BusinessConflictException extends BusinessException {

    public BusinessConflictException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT; // 409
    }
}