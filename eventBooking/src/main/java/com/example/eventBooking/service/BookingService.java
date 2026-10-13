package com.example.eventBooking.service;

import com.example.eventBooking.dto.response.BookingDetailResponse;
import com.example.eventBooking.dto.response.BookingResponse;
import com.example.eventBooking.dto.request.BookingRequest;

import java.util.List;

public interface BookingService {
    BookingResponse create(BookingRequest request);
    List<BookingResponse> getAll();

    BookingDetailResponse getById(Long id);
}
