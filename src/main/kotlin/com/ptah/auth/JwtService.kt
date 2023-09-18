package com.ptah.auth

import cn.hutool.jwt.JWTUtil
import com.ptah.common.impl.RedisService
import com.ptah.entity.userprofiling.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {
    @Autowired
    private val redisService: RedisService? = null

    fun extractUserName(jwt: String?): String? {
        return Optional.ofNullable(JWTUtil.parseToken(jwt).getPayload("login"))
            .map { obj: Any? -> String::class.java.cast(obj) }.orElse(null)
    }

    fun isTokenValid(jwt: String?, userDetails: UserDetails): Boolean {
        val isValid = JWTUtil.verify(jwt, "".toByteArray())
        val isExpired = redisService!!.hasKey(generateTokenKey(userDetails.username))
        return isValid && !isExpired
    }

    private fun generateTokenKey(login: String): String {
        return login
    }

    fun generateToken(user: User): String {
        val token = JWTUtil.createToken(mapOf("login" to user.login, "id" to user.id), "PTAH".toByteArray())
        redisService!!.setValue(generateTokenKey(user.login ?: ""), token)
        return token
    }
}