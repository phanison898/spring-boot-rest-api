package com.github.phanison898.cloudbox.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .and()
                .signWith(getSigningKey(System.getProperty("JWT_SECRET_KEY")))
                .compact();
    }

    private Key getSigningKey(String secretKey) {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("JWT_SECRET_KEY is not set");
        }

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        if (keyBytes.length < 32) { // 256 bits = 32 bytes
            throw new IllegalArgumentException("JWT_SECRET_KEY must be at least 256 bits (32 bytes)");
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
