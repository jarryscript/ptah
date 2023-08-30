package com.ptah.controller.user;

import com.ptah.auth.AuthenticationService;
import com.ptah.dto.userprofiling.LoginRequest;
import com.ptah.dto.userprofiling.LoginResponse;
import com.ptah.dto.userprofiling.RegisterRequest;
import com.ptah.dto.userprofiling.UserDto;
import com.ptah.service.user.UserService;
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
