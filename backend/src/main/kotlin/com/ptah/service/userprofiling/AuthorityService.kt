package com.ptah.service.userprofiling

import com.ptah.common.Errors
import com.ptah.common.exceptions.ApplicationException
import com.ptah.entity.userprofiling.Authority
import com.ptah.entity.userprofiling.AuthorityMapping
import com.ptah.entity.userprofiling.OrganizationRole
import com.ptah.entity.userprofiling.ProjectRole
import com.ptah.repository.userprofiling.AuthorityMappingRepository
import org.springframework.stereotype.Service

@Service
class AuthorityService(var authorizationMappingRepository: AuthorityMappingRepository) {

    fun addAuthorityMapping(authority: String, role: String) {
        validateAuthority(authority)
        validateRole(role)
        val authorityMapping = getAuthorityMappingByRole(role)
        val newAuthorities = HashSet(authorityMapping.authorities)
        newAuthorities.add(authority)
        authorityMapping.authorities = newAuthorities
        authorizationMappingRepository.save(authorityMapping)
    }

    fun removeAuthorityMapping(authority: String, role: String) {
        validateAuthority(authority)
        validateRole(role)
        val authorityMapping = getAuthorityMappingByRole(role)
        authorityMapping.authorities?.remove(authority)
        authorizationMappingRepository.save(authorityMapping)
    }

    private fun getAuthorityMappingByRole(role: String) =
        authorizationMappingRepository.findByRole(role) ?: AuthorityMapping(role = role)

    private fun validateAuthority(authority: String) {
        require(isValidAuthority(authority)) { throw ApplicationException.of(Errors.INVALID_AUTHORITY) }
    }

    private fun validateRole(role: String) {
        require(isValidRole(role)) { throw ApplicationException.of(Errors.INVALID_ROLE) }
    }

    private fun isValidAuthority(authority: String) =
        Authority.values().map { it.name.lowercase() }.any(authority.lowercase()::equals)

    private fun isValidRole(role: String) =
        ProjectRole.values().map { it.name.lowercase() }.plus(OrganizationRole.values().map { it.name.lowercase() })
            .any(role.lowercase()::equals)
}
