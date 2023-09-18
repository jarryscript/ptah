package com.ptah.service.userprofiling

import cn.hutool.core.lang.Validator
import com.ptah.common.*
import com.ptah.common.exceptions.ApplicationException
import com.ptah.dto.userprofiling.RegisterRequest
import com.ptah.dto.userprofiling.UserDto
import com.ptah.entity.userprofiling.*
import com.ptah.repository.project.ProjectNominationRepository
import com.ptah.repository.userprofiling.AuthorityMappingRepository
import com.ptah.repository.userprofiling.OrganizationNominationRepository
import com.ptah.repository.userprofiling.OrganizationRepository
import com.ptah.repository.userprofiling.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import java.util.function.Supplier
import java.util.stream.Collectors

@Service
class UserService {
    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val organizationRepository: OrganizationRepository? = null

    @Autowired
    private val authorityMappingRepository: AuthorityMappingRepository? = null

    @Autowired
    private val projectNominationRepository: ProjectNominationRepository? = null

    @Autowired
    private val organizationNominationRepository: OrganizationNominationRepository? = null
    fun register(registerRequest: RegisterRequest): UserDto {
        validateRegisterRequest(registerRequest)
        return createUser(registerRequest)
    }

    private fun createUser(registerRequest: RegisterRequest): UserDto {
        val savedUser = userRepository!!.save(
            User.builder().login(registerRequest.login).password(registerRequest.password).build()
        )
        return UserDto.builder().login(savedUser.login).build()
    }

    private fun validateRegisterRequest(registerRequest: RegisterRequest) {
        Validator.validateNotEmpty(registerRequest.login, "")
        Validator.validateNotEmpty(registerRequest.password, "")
    }

    fun getAuthorities(user: User): Set<GrantedAuthority> {
        val roles: MutableSet<*> = HashSet<Any?>()
        roles.addAll(
            projectNominationRepository!!.findByUserId(user.id).stream()
                .map { obj: ProjectNomination? -> obj.getProjectRole() }
                .map { obj: ProjectRole -> obj.name }.toList()
        )
        roles.addAll(
            organizationNominationRepository!!.findByUserId(user.id).stream()
                .map { obj: OrganizationNomination? -> obj.getOrganizationRole() }
                .toList())
        authorityMappingRepository!!.findByRoles(roles)
        return convertToGrantedAuthorities(authorityMappingRepository.findByRoles(roles))
    }

    private fun convertToGrantedAuthorities(authorityMappings: Set<AuthorityMapping?>?): Set<GrantedAuthority> {
        return authorityMappings!!.stream()
            .map { authorityMapping: AuthorityMapping? -> SimpleGrantedAuthority(authorityMapping.getRole()) }
            .collect(Collectors.toSet())
    }

    fun assignUserToOrganization(userId: Long?, organizationId: Long?) {
        if (organizationNominationRepository!!.exists<OrganizationNomination>(
                Example.of<OrganizationNomination>(
                    OrganizationNomination.builder().build()
                )
            )
        ) {
            throw ApplicationException.Companion.of(null)
        }
        organizationNominationRepository.save(OrganizationNomination.builder().build())
    }

    fun switchToOrganization(userId: Long, organizationId: Long) {
        val user = userRepository!!.findById(userId).orElseThrow<ApplicationException>(
            Supplier<ApplicationException> { ApplicationException.Companion.of(Errors.USER_NOT_FOUND) })!!
        val organization = organizationRepository!!.findById(organizationId).orElseThrow<ApplicationException>(
            Supplier<ApplicationException> { ApplicationException.Companion.of(Errors.ORGANIZATION_NOT_FOUND) })!!
        user.currentOrganization = organization
        userRepository.save(user)
    }
}
