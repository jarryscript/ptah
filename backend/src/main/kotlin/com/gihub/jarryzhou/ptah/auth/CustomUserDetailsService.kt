package com.gihub.jarryzhou.ptah.auth

import com.ptah.common.Errors
import com.ptah.repository.userprofiling.UserRepository
import com.ptah.service.userprofiling.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomUserDetailsService : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var userService: UserService

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails = userRepository.findByLogin(username)?.let { user ->
        return CustomUserDetails().apply {
            authorities = userService.getAuthorities(user)
            internalPassword = user.password
            id = user.id
        }
    } ?: throw UsernameNotFoundException(Errors.INVALID_CREDENTIALS.message)
}
