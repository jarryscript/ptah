package com.github.jarryzhou.ptah.auth

import cn.hutool.jwt.JWTUtil
import com.github.jarryzhou.ptah.common.impl.RedisService
import com.github.jarryzhou.ptah.entity.userprofiling.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

private const val ACCESS_TOKEN_EXPIRATION_TIME = 3600 * 24 * 3L

@Service
class JwtService {
    @Autowired
    lateinit var redisService: RedisService

    fun extractUserName(jwt: String?): String? = JWTUtil.parseToken(jwt).getPayload("login").toString()

    fun isTokenValid(jwt: String?, userDetails: UserDetails): Boolean {
        val isValid = JWTUtil.verify(jwt, "PTAH".toByteArray())
        val isExpired = redisService.hasKey(generateTokenKey(userDetails.username))
        return isValid && !isExpired
    }

    private fun generateTokenKey(login: String): String {
        return login
    }

    fun generateToken(user: User): String {
        val token = JWTUtil.createToken(mapOf("login" to user.login, "id" to user.id), "PTAH".toByteArray())
        redisService.setValue(generateTokenKey(user.login ?: ""), token, ACCESS_TOKEN_EXPIRATION_TIME)
        return token
    }
}
