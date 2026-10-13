package com.example.eventBooking.service;

import com.example.eventBooking.dto.request.UpdateProfileRequest;
import com.example.eventBooking.dto.response.ProfileResponse;
import com.example.eventBooking.dto.response.RegisterResponse;
import com.example.eventBooking.entity.User;

public interface   UserService {
     RegisterResponse toRegisterResponse(User user);
    ProfileResponse updateProfile(
            String email,
            UpdateProfileRequest request
    );

}
