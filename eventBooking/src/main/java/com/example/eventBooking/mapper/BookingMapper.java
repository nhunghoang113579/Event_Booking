package com.example.eventBooking.mapper;

import com.example.eventBooking.dto.response.BookingEventResponse;
import com.example.eventBooking.dto.response.BookingResponse;
import com.example.eventBooking.dto.request.BookingRequest;
import com.example.eventBooking.entity.Booking;
import com.example.eventBooking.repository.BookingRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")

public interface BookingMapper {
    Booking bookingToEntity(BookingRequest request);

    BookingResponse toResponse(Booking booking);


    }

