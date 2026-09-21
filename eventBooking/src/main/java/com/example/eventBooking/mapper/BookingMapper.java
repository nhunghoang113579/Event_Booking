package com.example.eventBooking.mapper;

import com.example.eventBooking.dto.BookingEventResponse;
import com.example.eventBooking.dto.BookingResponse;
import com.example.eventBooking.dto.BookingRequest;
import com.example.eventBooking.entity.Booking;
import com.example.eventBooking.repository.BookingRepository;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    private final BookingRepository bookingRepository;

    public BookingMapper(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking bookingtoEntity(BookingRequest request){
       Booking booking = new Booking();
       booking.setQuantity(request.getQuantity());

       return booking;

    }

    public BookingResponse toRespone(Booking booking) {
        BookingEventResponse eventResponse = new BookingEventResponse(
                booking.getEvent().getId(),
                booking.getEvent().getTitle(),
                booking.getEvent().getDateTime(),
                booking.getEvent().getLocation()
        );

        return new BookingResponse(booking.getId(),
                eventResponse,
                booking.getQuantity(),
                booking.getTotalPrice()
        );
    }
}
