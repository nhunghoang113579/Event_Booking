package com.example.eventBooking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ValidationErrorResponse {
    private boolean success;
    private String message;
    private List<FieldErrorResponse> errors;
}
