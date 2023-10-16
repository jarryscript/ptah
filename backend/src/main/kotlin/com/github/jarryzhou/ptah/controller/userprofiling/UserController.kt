package com.github.jarryzhou.ptah.controller.userprofiling

import com.github.jarryzhou.ptah.auth.AuthenticationService
import com.github.jarryzhou.ptah.dto.userprofiling.LoginRequest
import com.github.jarryzhou.ptah.dto.userprofiling.LoginResponse
import com.github.jarryzhou.ptah.dto.userprofiling.OrganizationNominationDTO
import com.github.jarryzhou.ptah.dto.userprofiling.RegisterRequest
import com.github.jarryzhou.ptah.dto.userprofiling.UserDto
import com.github.jarryzhou.ptah.dto.userprofiling.UserInfo
import com.github.jarryzhou.ptah.entity.userprofiling.OrganizationRole
import com.github.jarryzhou.ptah.service.userprofiling.UserService
import com.github.jarryzhou.ptah.util.SecurityUtil
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
    fun info(): UserInfo = userService.getUserInfo(SecurityUtil.currentUserId())

    @PostMapping("/organizationNomination")
    fun assignUserToOrganization(@RequestBody organizationNominationDTO: OrganizationNominationDTO) {
        userService.assignUserToOrganization(organizationNominationDTO.userId, organizationNominationDTO.organizationId, OrganizationRole.valueOf(organizationNominationDTO.organizationRole.uppercase()))
    }
}
