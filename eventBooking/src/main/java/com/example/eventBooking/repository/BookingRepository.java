package com.example.eventBooking.repository;

import com.example.eventBooking.dto.response.BookingResponse;
import com.example.eventBooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByUserId(Long id);
}
