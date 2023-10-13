package com.ptah.common.payload

enum class ResultCodeEnum(@JvmField val code: Int, @JvmField val message: String) {
    SUCCESS(200, "Success"),
    USER_NOT_FOUND(10001, "User Not Found"),
    PASSWORD_IS_WRONG(10002, "Password is wrong"),
    TOKEN_ERROR(10003, "Please login first")
}
