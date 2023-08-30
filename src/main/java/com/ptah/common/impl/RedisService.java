package com.ptah.common.impl;

import com.ptah.common.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedisService implements CacheService {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public String getByKey(String key) {
        return getByKey(key, String.class);
    }

    @Override
    public <T> T getByKey(String key, Class<T> type) {
        Object value = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(value).map(type::cast).orElseThrow();
    }

    @Override
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setValue(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout);
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
