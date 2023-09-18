package com.ptah.common

interface CacheService {
    fun <T> getByKey(key: String?, type: Class<T>): T
    fun getByKey(key: String?): String
    fun setValue(key: String, value: Any)
    operator fun setValue(key: String, value: Any, timeout: Long)
    fun hasKey(key: String): Boolean
}
