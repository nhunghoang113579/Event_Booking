package com.example.eventBooking.security;

import com.example.eventBooking.config.JwtConfig;
import com.example.eventBooking.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.util.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Getter
@RequiredArgsConstructor

public class JwtService {
    private final JwtConfig jwtConfig;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtConfig.getSecret())
        );
    }

    public String generrationToken(User user) {

        Instant now = Instant.now();

        Instant exp = now.plusSeconds((jwtConfig.getExpirationSeconds()));

        Map<String,Object> claims = new HashMap<>();
        if(user.getRole() != null){
            claims.put("role",user.getRole().name());
        }
        return Jwts.builder()
                .setIssuer(jwtConfig.getIssuer())
                .setSubject(user.getEmail())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .addClaims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractEmail(String token){
        return parseClaims(token).getSubject();
    }

      public String extractRole(String token){
        return parseClaims(token).get("role",String.class);
      }
      public boolean isTokenValid(String token){
        try{
            parseClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
      }


    public Instant getExpiration(String token) {

        Date expiration =
                parseClaims(token).getExpiration();

        return expiration.toInstant();
    }
}

