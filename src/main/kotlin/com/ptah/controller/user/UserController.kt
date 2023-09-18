package com.ptah.controller.user

import com.ptah.auth.AuthenticationService
import com.ptah.dto.userprofiling.LoginRequest
import com.ptah.dto.userprofiling.LoginResponse
import com.ptah.dto.userprofiling.RegisterRequest
import com.ptah.dto.userprofiling.UserDto
import com.ptah.service.userprofiling.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    private val authenticationService: AuthenticationService? = null

    @Autowired
    private val userService: UserService? = null
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): LoginResponse {
        return authenticationService!!.login(loginRequest.login, loginRequest.password)
    }

    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest): UserDto? {
        return userService!!.register(registerRequest)
    }
}
