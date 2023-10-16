package com.github.jarryzhou.ptah.entity.userprofiling

enum class Authority(private var description: String) {
    ADMIN("Administrator"),
    PLATFORM_ADMIN("Platform admin"),
    PROJECT_OWNER("Project owner")
}
