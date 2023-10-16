package com.github.jarryzhou.ptah.service.userprofiling

import com.github.jarryzhou.ptah.entity.userprofiling.Organization
import com.github.jarryzhou.ptah.entity.userprofiling.OrganizationNomination
import com.github.jarryzhou.ptah.entity.userprofiling.OrganizationRole
import com.github.jarryzhou.ptah.entity.userprofiling.User
import com.github.jarryzhou.ptah.repository.userprofiling.OrganizationNominationRepository
import org.springframework.stereotype.Service

@Service
class OrganizationNominationService(private var organizationNominationRepository: OrganizationNominationRepository) {

    fun updateOrganizationNomination(
        organizationNomination: OrganizationNomination,
        userId: Long,
        organizationId: Long,
        organizationRole: OrganizationRole
    ) = organizationNomination.apply {
        user = User().apply { id = userId }
        organization = Organization().apply {
            id = organizationId
        }
        this.organizationRole = organizationRole
    }

    fun getOrganizationNomination(
        userId: Long,
        organizationId: Long
    ) = (
        organizationNominationRepository.findByUserIdAndOrganizationId(userId, organizationId)
            ?: OrganizationNomination()
        )
}
