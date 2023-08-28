package com.ptah.auth;

import cn.hutool.jwt.JWTUtil;
import com.ptah.common.impl.RedisService;
import com.ptah.entity.userprofiling.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class JwtService {
    @Autowired
    private RedisService redisService;

    public String extractUserName(String jwt) {
        return Optional.ofNullable(JWTUtil.parseToken(jwt).getPayload("login")).map(String.class::cast).orElse(null);
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        boolean isValid = JWTUtil.verify(jwt, "".getBytes());
        boolean isExpired = redisService.hasKey(generateTokenKey(userDetails.getUsername()));
        return isValid && !isExpired;
    }

    private String generateTokenKey(String login) {
        return login;
    }

    public String generateToken(User user) {
        String token = JWTUtil.createToken(Map.of("login", user.getLogin(), "id", user.getId()), "".getBytes());
        redisService.setValue(generateTokenKey(user.getLogin()), token);
        return token;
    }
}
