package com.example.eventBooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEventResponse {

    private Long id;
    private String title;
    private LocalDateTime dateTime;
    private String location;
}