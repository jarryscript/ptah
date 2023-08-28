package com.ptah.auth;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WithAuthorityAspect {

    @Pointcut("@annotation(withAuthority)")
    public void hasPermissionInterceptor(WithAuthority withAuthority) {
    }

    @Before(value = "hasAuthorityInterceptor(withAuthority)", argNames = "joinPoint,withAuthority")
    public void hasPermissionCheck(JoinPoint joinPoint, WithAuthority withPermission) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new SecurityException("Authentication not found.");
        }

        boolean hasPermission = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals(withPermission.value()));

        if (!hasPermission) {
            throw new SecurityException("Insufficient permissions for method invocation.");
        }
    }
}
