package com.example.eventBooking.controller;

import com.example.eventBooking.dto.request.LoginRequest;
import com.example.eventBooking.dto.response.ApiResponse;
import com.example.eventBooking.dto.request.RegisterRequest;
import com.example.eventBooking.dto.response.LoginResponse;
import com.example.eventBooking.dto.response.RegisterResponse;
import com.example.eventBooking.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request){
        RegisterResponse register = authService.register(request);

        ApiResponse<RegisterResponse>response = new ApiResponse<>(
                true,
                "Registration successful",
                register

        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @PostMapping("/login")
    public  ResponseEntity<ApiResponse<LoginResponse>>login(@Valid @RequestBody LoginRequest request){
        LoginResponse login = authService.login(request);

        ApiResponse<LoginResponse>response = new ApiResponse<>(
                true,
                "Login successful",
                login
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


}
