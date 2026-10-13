package com.example.eventBooking.repository;

import com.example.eventBooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Booking,Long> {
    List<Booking>findByUserId(Long UserId);
}
