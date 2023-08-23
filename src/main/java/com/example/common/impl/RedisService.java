package com.example.common.impl;

import com.example.common.ObjectStorageService;

public class RedisService implements ObjectStorageService {
    @Override
    public <T> T getByKey(String key, Class<T> type) {
        return null;
    }

    @Override
    public String getByKey(String key) {
        return null;
    }

    @Override
    public void setValue(String key, Object value) {

    }

    @Override
    public void setValue(String key, Object value, long timeout) {

    }
}
