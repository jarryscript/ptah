package com.ptah.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService, private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(AUTHORIZATION_HEADER)
        if (containsAuthorizationHeader(authHeader)) {
            val jwt = getJwt(authHeader)
            val userEmail = getUserEmail(jwt)
            if (hasNoSecurityContext()) {
                val userDetails = userDetailsService.loadUserByUsername(userEmail)
                if (isTokenValid(jwt, userDetails)) {
                    setSecurityContext(request, userDetails)
                }
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun hasNoSecurityContext(): Boolean = SecurityContextHolder.getContext().authentication == null

    private fun isTokenValid(jwt: String, userDetails: UserDetails): Boolean = jwtService.isTokenValid(jwt, userDetails)

    private fun getUserEmail(jwt: String): String? = jwtService.extractUserName(jwt)

    private fun getJwt(authHeader: String): String = authHeader.substring(JWT_BEGIN_INDEX)

    private fun containsAuthorizationHeader(authHeader: String?): Boolean =
        org.apache.commons.lang3.StringUtils.startsWith(authHeader, AUTHENTICATION_HEADER_PREFIX)

    private fun setSecurityContext(request: HttpServletRequest, userDetails: UserDetails) {
        val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = authToken
        SecurityContextHolder.setContext(context)
    }

    companion object {
        const val AUTHENTICATION_HEADER_PREFIX = "Bearer "
        const val AUTHORIZATION_HEADER = "Authorization"
        const val JWT_BEGIN_INDEX = 7
    }
}
