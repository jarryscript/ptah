package com.ptah.service.userprofiling

import cn.hutool.core.lang.Validator
import com.ptah.common.*
import com.ptah.common.exceptions.ApplicationException
import com.ptah.dto.userprofiling.RegisterRequest
import com.ptah.dto.userprofiling.UserDto
import com.ptah.dto.userprofiling.UserInfo
import com.ptah.entity.userprofiling.*
import com.ptah.repository.project.ProjectNominationRepository
import com.ptah.repository.userprofiling.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import kotlin.collections.HashSet

@Service
class UserService(
    private var userRepository: UserRepository,
    private var userJournalRepository: UserJournalRepository,
    private var organizationRepository: OrganizationRepository,
    private var authorityMappingRepository: AuthorityMappingRepository,
    private var projectNominationRepository: ProjectNominationRepository,
    private var organizationNominationService: OrganizationNominationService,
    private var organizationNominationRepository: OrganizationNominationRepository


) {
    fun register(registerRequest: RegisterRequest): UserDto {
        validateRegisterRequest(registerRequest)
        return createUser(registerRequest)
    }

    private fun createUser(registerRequest: RegisterRequest) = UserDto(
        login = userRepository.save(
            User(
                password = registerRequest.password, login = registerRequest.login
            )
        ).login
    )

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
                it?.projectRole?.name?.lowercase()
            })
            addAll(organizationNominationRepository.findByUserId(user.id).mapNotNull {
                it?.organizationRole?.name?.lowercase()
            })
            this
        }
        return roles
    }

    private fun convertToGrantedAuthorities(authorityMappings: Set<AuthorityMapping?>): MutableSet<GrantedAuthority> {
        return authorityMappings.filterNotNull().flatMap { it.authorities!! }.map { SimpleGrantedAuthority(it) }
            .toMutableSet()
    }

    fun assignUserToOrganization(userId: Long, organizationId: Long, organizationRole: OrganizationRole) {
        val organizationNomination = organizationNominationService.getOrganizationNomination(userId, organizationId)
        organizationNominationService.updateOrganizationNomination(
            organizationNomination, userId, organizationId, organizationRole
        )
        organizationNominationRepository.save(organizationNomination)
    }


    fun switchToOrganization(userId: Long, organizationId: Long) {
        val user: User = getUser(userId)
        user.currentOrganization = organizationRepository.findById(organizationId)
            .orElseThrow { ApplicationException.of(Errors.ORGANIZATION_NOT_FOUND) }
        userRepository.save(user)
    }

    fun getUserInfo(userId: Long): UserInfo {
        val user: User = getUser(userId)
        val authorities = getAuthorities(user).map { it.authority }
        val userJournals = userJournalRepository.findByUserId(userId)
        return UserInfo().apply {
            this.user = UserDto().apply {
                this.authorities = authorities
                login = user.login
                nickname = user.name
            }
            logs = userJournals
        }
    }

    private fun getUser(userId: Long): User {
        return userRepository.findById(userId).orElseThrow { ApplicationException.of(Errors.USER_NOT_FOUND) }!!
    }

    fun updateUser(userDto: UserDto): UserDto {
        userRepository.save(userDto.toEntity(User::class))
        return userDto
    }

}