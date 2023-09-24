package com.ptah.controller.user

import com.ptah.auth.AuthenticationService
import com.ptah.dto.userprofiling.*
import com.ptah.service.userprofiling.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    lateinit var authenticationService: AuthenticationService

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): LoginResponse =
        authenticationService.login(loginRequest.login, loginRequest.password)

    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest): UserDto? = userService.register(registerRequest)

    @GetMapping("/info")
    fun info() : UserInfo = userService.getUserInfo(1L);
}
