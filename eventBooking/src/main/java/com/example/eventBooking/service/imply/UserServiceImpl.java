package com.example.eventBooking.service.imply;

import com.example.eventBooking.dto.request.UpdateProfileRequest;
import com.example.eventBooking.dto.response.ProfileResponse;
import com.example.eventBooking.dto.response.RegisterResponse;
import com.example.eventBooking.entity.User;
import com.example.eventBooking.exception.ResourceNotFoundException;
import com.example.eventBooking.mapper.UserMapper;
import com.example.eventBooking.repository.UserRepository;
import com.example.eventBooking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

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

    @Override
    public ProfileResponse updateProfile(String email, UpdateProfileRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        user.setFullName(request.getFullName());
        user.setAvatar(
                request.getAvatar() == null
                        || request.getAvatar().isBlank()
                        ? null
                        : request.getAvatar()
        );

        User savedUser = userRepository.save(user);

        return new ProfileResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getAvatar()
        );
    }
    }
