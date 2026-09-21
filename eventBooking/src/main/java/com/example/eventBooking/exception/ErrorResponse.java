package com.example.eventBooking.exception;

public record ErrorResponse(
        boolean success,
        String message
) {
}