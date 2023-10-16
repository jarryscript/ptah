package com.github.jarryzhou.ptah.auth

import com.ptah.dto.userprofiling.LoginResponse
import com.ptah.repository.userprofiling.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    var userRepository: UserRepository,
    var jwtService: JwtService,
    var authenticationManager: AuthenticationManager
) {
    fun login(login: String?, password: String?): LoginResponse {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(login, password))
        val user = userRepository.findByLogin(login) ?: throw IllegalArgumentException("Invalid email or password.")
        return LoginResponse(accessToken = jwtService.generateToken(user))
    }
}
