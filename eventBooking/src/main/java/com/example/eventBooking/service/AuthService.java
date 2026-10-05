package com.example.eventBooking.service;

import com.example.eventBooking.dto.request.LoginRequest;
import com.example.eventBooking.dto.request.RegisterRequest;
import com.example.eventBooking.dto.response.LoginResponse;
import com.example.eventBooking.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login (LoginRequest request);
}
