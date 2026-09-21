package com.example.eventBooking.dto;

import java.math.BigDecimal;

public record Coordinates(
        BigDecimal latitude,
        BigDecimal longitude
) {
}