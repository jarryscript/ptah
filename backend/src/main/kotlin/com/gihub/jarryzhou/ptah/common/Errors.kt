package com.gihub.jarryzhou.ptah.common

enum class Errors(var code: String, @JvmField var message: String) {
    INVALID_CREDENTIALS("10000", "Invalid credentials"),
    INSUFFICIENT_FUND("10001", "Insufficient fund"),
    USER_NOT_FOUND("10002", "User not found"),
    ORGANIZATION_NOT_FOUND("10003", "Organization not found"),
    SYSTEM_ACCOUNT_NOT_FOUND("10003", "System account not found"),
    INVALID_AUTHORITY("10004", "Invalid authority"),
    INVALID_ROLE("10005", "Invalid role")
}
