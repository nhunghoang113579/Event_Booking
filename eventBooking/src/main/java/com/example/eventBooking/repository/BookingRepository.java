package com.example.eventBooking.repository;

import com.example.eventBooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    long countByEventId(Long eventId);
}
