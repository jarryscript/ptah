package com.example.auth;


import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String AUTHENTICATION_HEADER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final int JWT_BEGIN_INDEX = 7;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (containsAuthorizationHeader(authHeader)) {
            final String jwt = getJwt(authHeader);
            final String userEmail = getUserEmail(jwt);
            if (hasNoSecurityContext()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                if (isTokenValid(jwt, userDetails)) {
                    setSecurityContext(request, userDetails);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean hasNoSecurityContext() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private boolean isTokenValid(String jwt, UserDetails userDetails) {
        return jwtService.isTokenValid(jwt, userDetails);
    }

    private String getUserEmail(String jwt) {
        return jwtService.extractUserName(jwt);
    }

    private String getJwt(String authHeader) {
        return authHeader.substring(JWT_BEGIN_INDEX);
    }

    private boolean containsAuthorizationHeader(String authHeader) {
        return StringUtils.startsWith(authHeader, AUTHENTICATION_HEADER_PREFIX);
    }

    private void setSecurityContext(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
    }
}
