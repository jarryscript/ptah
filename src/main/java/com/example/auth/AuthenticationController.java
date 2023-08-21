package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return authenticationService.login(loginRequest.getLogin(),loginRequest.getPassword());
    }

    @PostMapping
    public LoginResponse register(@RequestBody LoginRequest loginRequest){
        return authenticationService.login(loginRequest.getLogin(),loginRequest.getPassword());
    }


}
