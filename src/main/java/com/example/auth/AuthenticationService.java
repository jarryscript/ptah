package com.example.auth;

import com.example.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(String login, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        var user = userRepository.findByLogin(login).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        return LoginResponse.builder().accessToken(jwtService.generateToken(user)).build();
    }

}
