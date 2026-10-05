package com.example.eventBooking.service.imply;

import com.example.eventBooking.dto.request.LoginRequest;
import com.example.eventBooking.dto.response.LoginResponse;
import com.example.eventBooking.entity.Role;
import com.example.eventBooking.exception.BusinessConflictException;
import com.example.eventBooking.dto.request.RegisterRequest;
import com.example.eventBooking.dto.response.RegisterResponse;
import com.example.eventBooking.entity.User;
import com.example.eventBooking.exception.BusinessConflictException;
import com.example.eventBooking.exception.BusinessValidationException;
import com.example.eventBooking.mapper.UserMapper;
import com.example.eventBooking.repository.UserRepository;
import com.example.eventBooking.security.JwtService;
import com.example.eventBooking.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor

public class AuthServiceImply implements AuthService {
  private final   UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;
  private final JwtService jwtService;


    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessConflictException("Email already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        User userSaved = userRepository.save(user);

        return userMapper.toRegisterResponse(userSaved);
    }


    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessConflictException("Invalid email or password"));

        boolean passwordCorrect = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordCorrect) {
            throw new BusinessValidationException("Invalid username or password");
        }

        String token = jwtService.generrationToken(user);
        // 4. Lấy thời gian hết hạn của JWT
        Instant exp = jwtService.getExpiration(token);

        return new LoginResponse(token,exp);

    }
}
