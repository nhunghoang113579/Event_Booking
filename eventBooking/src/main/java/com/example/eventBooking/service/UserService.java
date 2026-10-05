package com.example.eventBooking.service;

import com.example.eventBooking.dto.response.RegisterResponse;
import com.example.eventBooking.entity.User;

public interface   UserService {
     RegisterResponse toRegisterResponse(User user);

}
