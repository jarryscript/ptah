package com.example.auth;

import com.example.entity.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String jwt);

    boolean isTokenValid(String jwt, UserDetails userDetails);

    String generateToken(User user);
}
