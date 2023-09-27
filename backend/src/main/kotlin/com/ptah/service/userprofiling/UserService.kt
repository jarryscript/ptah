package com.ptah.service.userprofiling

import cn.hutool.core.lang.Validator
import com.ptah.common.*
import com.ptah.common.exceptions.ApplicationException
import com.ptah.dto.userprofiling.RegisterRequest
import com.ptah.dto.userprofiling.UserDto
import com.ptah.dto.userprofiling.UserInfo
import com.ptah.entity.userprofiling.*
import com.ptah.repository.project.ProjectNominationRepository
import com.ptah.repository.userprofiling.AuthorityMappingRepository
import com.ptah.repository.userprofiling.OrganizationNominationRepository
import com.ptah.repository.userprofiling.OrganizationRepository
import com.ptah.repository.userprofiling.UserRepository
import org.springframework.data.domain.Example
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import java.util.HashSet

@Service
class UserService(
    var userRepository: UserRepository,

    var organizationRepository: OrganizationRepository,

    var authorityMappingRepository: AuthorityMappingRepository,

    var projectNominationRepository: ProjectNominationRepository,

    var organizationNominationRepository: OrganizationNominationRepository

) {
    fun register(registerRequest: RegisterRequest): UserDto {
        validateRegisterRequest(registerRequest)
        return createUser(registerRequest)
    }

    private fun createUser(registerRequest: RegisterRequest): UserDto = UserDto().apply {
        login = userRepository.save(User().apply {
            login = registerRequest.login
            password = registerRequest.password
        }).login
    }

    private fun validateRegisterRequest(registerRequest: RegisterRequest) {
        Validator.validateNotEmpty(registerRequest.login, "")
        Validator.validateNotEmpty(registerRequest.password, "")
    }

    fun getAuthorities(user: User): Set<GrantedAuthority> {
        val roles = getRolesOfUser(user)
        val authorityMappings = getUserAuthorityMappings(roles)
        val authorities = convertToGrantedAuthorities(authorityMappings)
        authorities.addAll(roles.map { SimpleGrantedAuthority(it) })
        return authorities
    }

    private fun getUserAuthorityMappings(roles: Set<String>): Set<AuthorityMapping?> {
        return authorityMappingRepository.findByRoles(roles)
    }

    private fun getRolesOfUser(user: User): MutableSet<String> {
        val roles: MutableSet<String> = with(HashSet<String>()) {
            addAll(projectNominationRepository.findByUserId(user.id).mapNotNull {
                it?.projectRole?.name
            })
            addAll(organizationNominationRepository.findByUserId(user.id).mapNotNull {
                it?.organizationRole?.name
            })
            this
        }
        return roles
    }

    private fun convertToGrantedAuthorities(authorityMappings: Set<AuthorityMapping?>): MutableSet<GrantedAuthority> {
        return authorityMappings.mapNotNull { authorityMapping ->
            authorityMapping?.role?.let { SimpleGrantedAuthority(it) }
        }.toMutableSet()
    }

    fun assignUserToOrganization(userId: Long?, organizationId: Long?) {
        if (organizationNominationRepository.exists(
                Example.of(
                    OrganizationNomination()
                )
            )
        ) {
            throw ApplicationException.of(null)
        }
        organizationNominationRepository.save(OrganizationNomination())
    }

    fun switchToOrganization(userId: Long, organizationId: Long) {
        val user: User =
            userRepository.findById(userId).orElseThrow { ApplicationException.of(Errors.USER_NOT_FOUND) }!!
        val organization = organizationRepository.findById(organizationId)
            .orElseThrow { ApplicationException.of(Errors.ORGANIZATION_NOT_FOUND) }
        user.currentOrganization = organization
        userRepository.save(user)
    }

    fun getUserInfo(userId: Long): UserInfo {
        val user: User =
            userRepository.findById(userId).orElseThrow { ApplicationException.of(Errors.USER_NOT_FOUND) }!!
        val authorities = getAuthorities(user).map { it.authority }
        return UserInfo().apply {
            this.user = UserDto().apply {
                this.authorities = authorities
                login = user.login
                nickname = user.name
            }
            logs = emptyList()
        }
    }

    fun updateUser(userDto: UserDto): UserDto {
        userRepository.save(userDto.toEntity(User::class))
        return userDto
    }


}