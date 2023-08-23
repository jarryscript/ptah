package com.example.controller.user;

import com.example.auth.AuthenticationService;
import com.example.dto.user.LoginRequest;
import com.example.dto.user.LoginResponse;
import com.example.dto.user.RegisterRequest;
import com.example.dto.user.UserDto;
import com.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest.getLogin(), loginRequest.getPassword());
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }
}
