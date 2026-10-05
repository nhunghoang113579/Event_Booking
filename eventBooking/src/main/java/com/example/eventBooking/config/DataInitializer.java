package com.example.eventBooking.config;



import com.example.eventBooking.entity.Role;
import com.example.eventBooking.entity.User;
import com.example.eventBooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner createAdmin() {

        return args -> {

            if (!userRepository.existsByEmail("admin@gmail.com")) {

                User admin = User.builder()
                        .fullName("System Admin")
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("Admin123"))
                        .role(Role.ADMIN)
                        .createdAt(LocalDateTime.now())
                        .build();

                userRepository.save(admin);

                System.out.println("Admin account created.");
            }
        };
    }
}