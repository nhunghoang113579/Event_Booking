package com.example.eventBooking.validation;

import com.example.eventBooking.dto.request.BookingRequest;
import com.example.eventBooking.dto.response.FieldError;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingQuantityValidator {

    public void validate(
            BookingRequest request,
            List<FieldError> errors) {

        if (request.getQuantity() == null) {
            errors.add(
                    new FieldError(
                            "quantity",
                            "Quantity is required"
                    )
            );
            return;
        }

        if (request.getQuantity() < 1) {
            errors.add(
                    new FieldError(
                            "quantity",
                            "Quantity must be at least 1"
                    )
            );
        }
    }
}