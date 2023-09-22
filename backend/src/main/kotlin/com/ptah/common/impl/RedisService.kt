package com.ptah.common.impl

import com.ptah.common.CacheService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class RedisService : CacheService {
    @Autowired
    lateinit var redisTemplate: RedisTemplate<Any, Any>
    override fun getByKey(key: String?): String = getByKey(key, String::class.java)


    override fun <T> getByKey(key: String?, type: Class<T>): T {
        val value = redisTemplate.opsForValue()[key!!]
        return Optional.ofNullable(value).map { obj: Any? -> type.cast(obj) }.orElseThrow()
    }

    override fun setValue(key: String, value: Any) {
        redisTemplate.opsForValue()[key] = value
    }

    override fun setValue(key: String, value: Any, timeout: Long) {
        redisTemplate.opsForValue()[key, value] = timeout
    }

    override fun hasKey(key: String): Boolean {
        return redisTemplate.hasKey(key)
    }
}
