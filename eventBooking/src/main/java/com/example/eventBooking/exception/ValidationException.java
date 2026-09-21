package com.example.eventBooking.exception;

import lombok.Getter;

import java.util.List;
@Getter
public class ValidationException extends RuntimeException {
      public final  List<FieldErrorResponse> errors;
            public ValidationException(List<FieldErrorResponse> errors) {
            super("Validation failed");
            this.errors = errors;
        }
    }




