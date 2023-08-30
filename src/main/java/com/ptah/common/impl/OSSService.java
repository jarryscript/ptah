package com.ptah.common.impl;

import cn.hutool.core.util.SerializeUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ptah.common.Errors;
import com.ptah.common.ObjectStorageService;
import com.ptah.common.exceptions.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class OSSService implements ObjectStorageService {
    @Value("${oss.endpoint}")
    private String ossEndpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Override
    public <T> T getByKey(String key, Class<T> type) {
        OSS ossClient = null;
        try {
            ossClient = getOssClient();
            return SerializeUtil.deserialize(ossClient.getObject(bucketName, key).getObjectContent().readAllBytes(), type);
        } catch (Exception e) {
            throw new ApplicationException(Errors.INVALID_CREDENTIALS);
        } finally {
            Optional.ofNullable(ossClient).ifPresent(OSS::shutdown);
        }
    }

    private OSS getOssClient() {
        return new OSSClientBuilder().build(ossEndpoint, accessKeyId, accessKeySecret);
    }

    @Override
    public void setValue(String key, Object value) {

    }

    @Override
    public void setValue(String key, Object value, long timeout) {

    }

    @Override
    public boolean hasKey(String key) {
        return false;
    }
}
