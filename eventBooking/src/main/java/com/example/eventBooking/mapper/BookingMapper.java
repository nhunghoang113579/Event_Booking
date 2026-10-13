package com.example.eventBooking.mapper;

import com.example.eventBooking.dto.response.BookingDetailResponse;
import com.example.eventBooking.dto.response.BookingEventResponse;
import com.example.eventBooking.dto.response.BookingResponse;
import com.example.eventBooking.dto.request.BookingRequest;
import com.example.eventBooking.entity.Booking;
import com.example.eventBooking.repository.BookingRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")


public interface BookingMapper {
    Booking bookingToEntity(BookingRequest request);

    @Mapping(source = "id",target = "bookingId")
    BookingResponse toResponse(Booking booking);

    @Mapping(source = "id", target = "bookingId")
    @Mapping(source = "status", target = "status")
    BookingDetailResponse toDetailResponse(Booking booking);


    }

