package com.example.eventBooking.validation;

import com.example.eventBooking.dto.request.BookingRequest;
import com.example.eventBooking.dto.response.FieldError;
import com.example.eventBooking.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingEventValidator {

    private final EventRepository eventRepository;

    public void validate(
            BookingRequest request,
            java.util.List<FieldError> errors) {

        if (request.getEventId() == null) {
            errors.add(
                    new FieldError(
                            "eventId",
                            "Event is required"
                    )
            );
            return;
        }

        if (request.getEventId() <= 0) {
            errors.add(
                    new FieldError(
                            "eventId",
                            "Event ID must be greater than 0"
                    )
            );
            return;
        }

        if (!eventRepository.existsById(request.getEventId())) {
            errors.add(
                    new FieldError(
                            "eventId",
                            "Event not found"
                    )
            );
        }
    }
}