package com.ptah.common

interface ObjectStorageService {
    fun <T> getByKey(key: String?, type: Class<T>?): T
    fun getByKey(key: String?): String? = getByKey(key, String::class.java)
    fun setValue(key: String?, value: Any?)
    operator fun setValue(key: String?, value: Any?, timeout: Long)
    fun hasKey(key: String?): Boolean
}
