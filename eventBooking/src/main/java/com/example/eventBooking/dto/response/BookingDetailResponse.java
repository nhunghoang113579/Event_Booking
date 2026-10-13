
package com.example.eventBooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailResponse {

    private Long bookingId;

    private BookingEventResponse event;

    private Integer quantity;

    private BigDecimal totalPrice;

    private String status;
}
