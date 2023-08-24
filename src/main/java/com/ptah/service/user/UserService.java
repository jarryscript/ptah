package com.ptah.service.user;

import cn.hutool.core.lang.Validator;
import com.ptah.dto.user.RegisterRequest;
import com.ptah.dto.user.UserDto;
import com.ptah.entity.user.User;
import com.ptah.repository.user.UserRepository;
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
        Validator.validateNotEmpty(registerRequest.getLogin(),"");
        Validator.validateNotEmpty(registerRequest.getPassword(),"");
    }
}
