package com.example.service.user;

import com.example.dto.user.RegisterRequest;
import com.example.dto.user.UserDto;
import com.example.entity.user.User;
import com.example.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto register(RegisterRequest registerRequest) {
        validateRegisterRequest(registerRequest);
        return createUser(registerRequest);
    }

    private UserDto createUser(RegisterRequest registerRequest) {
        User savedUser = userRepository.save(User.builder().login(registerRequest.getLogin()).password(registerRequest.getPassword()).build());
        return UserDto.builder().login(savedUser.getLogin()).build();
    }

    private void validateRegisterRequest(RegisterRequest registerRequest) {

    }
}
