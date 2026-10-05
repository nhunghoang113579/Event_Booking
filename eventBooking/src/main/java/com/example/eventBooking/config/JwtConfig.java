package com.example.eventBooking.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter

public class JwtConfig {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-seconds}")
    private long expirationSeconds;

    @Value("${app.jwt.issuer}")
    private String issuer;


}