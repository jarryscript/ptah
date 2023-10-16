package com.github.jarryzhou.ptah.util

import com.github.jarryzhou.ptah.auth.CustomUserDetails
import com.github.jarryzhou.ptah.common.Errors
import com.github.jarryzhou.ptah.common.exceptions.ApplicationException
import org.springframework.security.core.context.SecurityContextHolder

class SecurityUtil {
    companion object {
        fun currentUserId(): Long {
            SecurityContextHolder.getContext().authentication?.takeIf { it.isAuthenticated && it.principal is CustomUserDetails }
                ?.let { return (it.principal as CustomUserDetails).id!! }
            throw ApplicationException(Errors.INVALID_CREDENTIALS)
        }
    }
}
