package com.example.eventBooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
@AllArgsConstructor
@Getter

public class LoginResponse {
    private String accessToken;

    private Instant expire;


}
