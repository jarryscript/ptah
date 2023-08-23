package com.example.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String login;
    private String password;
}
