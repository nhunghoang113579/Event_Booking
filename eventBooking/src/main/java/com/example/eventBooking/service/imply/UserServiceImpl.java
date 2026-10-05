package com.example.eventBooking.service.imply;

import com.example.eventBooking.dto.response.RegisterResponse;
import com.example.eventBooking.entity.User;
import com.example.eventBooking.mapper.UserMapper;
import com.example.eventBooking.service.UserService;
import org.springframework.stereotype.Component;


@Component
public class UserServiceImpl implements UserService {

    @Override
    public RegisterResponse toRegisterResponse(User user) {
        if (user == null) {
            return null;
        }

        RegisterResponse registerResponse = new RegisterResponse();

        registerResponse.setEmail(user.getEmail());
        registerResponse.setFullName(user.getFullName());

        return registerResponse;
    }
}