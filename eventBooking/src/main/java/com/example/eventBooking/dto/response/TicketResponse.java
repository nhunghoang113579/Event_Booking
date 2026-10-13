package com.example.eventBooking.dto.response;

import com.example.eventBooking.entity.BookingStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class TicketResponse {
    private Long ticketId;

    private BookingEventResponse event;

    private Integer quantity;

    private BookingStatus status;

}
