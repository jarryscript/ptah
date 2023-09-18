package com.ptah.auth

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Aspect
@Component
class WithAuthorityAspect {
    @Pointcut("@annotation(withAuthority)")
    fun hasAuthorityInterceptor(withAuthority: WithAuthority?) {
    }

    @Before(value = "hasAuthorityInterceptor(withAuthority)", argNames = "joinPoint,withAuthority")
    fun hasPermissionCheck(joinPoint: JoinPoint?, withPermission: WithAuthority) {
        val auth = SecurityContextHolder.getContext().authentication
            ?: throw SecurityException("Authentication not found.")
        val hasPermission = auth.authorities.stream()
            .map { obj: GrantedAuthority -> obj.authority }
            .anyMatch { role: String -> role == withPermission.value }
        if (!hasPermission) {
            throw SecurityException("Insufficient permissions for method invocation.")
        }
    }
}
