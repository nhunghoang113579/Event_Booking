package com.example.eventBooking.service;

import com.example.eventBooking.dto.response.TicketResponse;
import com.example.eventBooking.entity.Booking;

import java.util.List;

public interface TicketService {
    List<TicketResponse>getTickets();
}
