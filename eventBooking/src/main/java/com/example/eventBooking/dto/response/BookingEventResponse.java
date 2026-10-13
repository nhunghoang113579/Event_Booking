package com.example.eventBooking.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEventResponse {

    private Long id;
    private String title;
    private LocalDateTime dateTime;
    private String location;
}