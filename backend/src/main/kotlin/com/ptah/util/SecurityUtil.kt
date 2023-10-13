package com.ptah.util

import com.ptah.auth.CustomUserDetails
import com.ptah.common.Errors
import com.ptah.common.exceptions.ApplicationException
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
