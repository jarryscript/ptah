package com.gihub.jarryzhou.ptah.common.impl

import cn.hutool.core.util.SerializeUtil
import com.aliyun.oss.OSS
import com.aliyun.oss.OSSClientBuilder
import com.ptah.common.Errors
import com.ptah.common.ObjectStorageService
import com.ptah.common.exceptions.ApplicationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class OSSService : ObjectStorageService {
    @Value("\${oss.endpoint:}")
    private val ossEndpoint: String? = null

    @Value("\${oss.accessKeyId:}")
    private val accessKeyId: String? = null

    @Value("\${oss.accessKeySecret:}")
    private val accessKeySecret: String? = null

    @Value("\${oss.bucketName:}")
    private val bucketName: String? = null
    override fun <T> getByKey(key: String?, type: Class<T>?): T {
        var ossClient: OSS? = null
        return try {
            ossClient = this.ossClient
            SerializeUtil.deserialize(ossClient.getObject(bucketName, key).objectContent.readAllBytes(), type)
        } catch (e: Exception) {
            throw ApplicationException(Errors.INVALID_CREDENTIALS)
        } finally {
            Optional.ofNullable(ossClient).ifPresent { obj: OSS -> obj.shutdown() }
        }
    }

    private val ossClient: OSS
        get() = OSSClientBuilder().build(ossEndpoint, accessKeyId, accessKeySecret)

    override fun setValue(key: String?, value: Any?) {}
    override fun setValue(key: String?, value: Any?, timeout: Long) {}
    override fun hasKey(key: String?): Boolean {
        return false
    }
}
