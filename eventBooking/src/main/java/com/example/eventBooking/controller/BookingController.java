package com.example.eventBooking.controller;

import com.example.eventBooking.dto.ApiResponse;
import com.example.eventBooking.dto.BookingRequest;
import com.example.eventBooking.dto.BookingResponse;
import com.example.eventBooking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookingResponse>> create(
            @Valid @RequestBody BookingRequest request) {

        BookingResponse booking = bookingService.create(request);

        ApiResponse<BookingResponse>response = new ApiResponse<>(
                true,
                "Booking successful",
                booking

        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAll() {

        List<BookingResponse> responses = bookingService.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }

    @GetMapping("/{eventId}/count")
    public ResponseEntity<Map<String, Object>> getBookingCount(
            @PathVariable Long eventId) {

        long count = bookingService.countByEventId(eventId);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Booking count retrieved successfully");
        response.put("data", count);

        return ResponseEntity.ok(response);
    }
}