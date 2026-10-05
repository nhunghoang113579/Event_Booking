package com.example.eventBooking.config;

import io.jsonwebtoken.Jwts;

import java.util.Base64;

public class SecretKeyGenerator {

    public static void main(String[] args) {

        byte[] key = Jwts.SIG.HS256.key()
                .build()
                .getEncoded();

        String secret = Base64.getEncoder()
                .encodeToString(key);

        System.out.println(secret);
    }
}