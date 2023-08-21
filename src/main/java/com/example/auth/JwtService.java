package com.example.auth;

import org.springframework.security.core.userdetails.UserDetails;

public class JwtService {
    public String extractUserName(String jwt) {
        return null;
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        return true;
    }

    public String generateToken(Object user) {
        return null;
    }
}
