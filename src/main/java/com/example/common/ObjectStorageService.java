package com.example.common;

public interface ObjectStorageService {
    <T> T getByKey(String key, Class<T> type);

    String getByKey(String key);

    void setValue(String key, Object value);

    void setValue(String key, Object value, long timeout);
}
