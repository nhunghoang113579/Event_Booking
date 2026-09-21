package com.example.eventBooking.service;

import com.example.eventBooking.dto.BookingResponse;
import com.example.eventBooking.dto.BookingRequest;

import java.util.List;

public interface BookingService {
    BookingResponse create(BookingRequest request);
    List<BookingResponse> getAll();
    long countByEventId(Long eventId);
}
