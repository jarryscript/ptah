package com.example.auth;

import com.example.common.impl.RedisService;
import com.example.entity.user.User;
import com.example.common.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

public class RedisJwtService implements JwtService{
    @Autowired
    private RedisService redisService;
    @Override
    public String extractUserName(String jwt) {
        return TokenUtils.extractUserName(jwt);
    }

    @Override
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        redisService.getByKey(TokenUtils.generateTokenKey(userDetails.getUsername()));
        return false;
    }

    @Override
    public String generateToken(User user) {
        String token = null;
        redisService.setValue("user_"+user.getId(),token);
        return token;
    }
}
