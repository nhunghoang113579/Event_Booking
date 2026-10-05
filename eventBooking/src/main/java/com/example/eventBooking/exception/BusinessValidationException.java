package com.example.eventBooking.exception;

import org.springframework.http.HttpStatus;

public class BusinessValidationException extends RuntimeException {

    public BusinessValidationException(String message) {
        super(message);
    }
}
