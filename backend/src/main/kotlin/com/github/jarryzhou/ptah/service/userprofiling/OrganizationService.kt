package com.github.jarryzhou.ptah.service.userprofiling

import com.github.jarryzhou.ptah.dto.userprofiling.CreateOrganizationRequest
import com.github.jarryzhou.ptah.dto.userprofiling.CreateOrganizationResponse
import com.github.jarryzhou.ptah.entity.userprofiling.Organization
import com.github.jarryzhou.ptah.entity.userprofiling.OrganizationRole
import com.github.jarryzhou.ptah.repository.userprofiling.OrganizationRepository
import com.github.jarryzhou.ptah.util.SecurityUtil
import org.springframework.stereotype.Service

@Service
class OrganizationService(
    private var organizationRepository: OrganizationRepository,
    private var userService: UserService
) {

    fun createOrganization(createOrganizationRequest: CreateOrganizationRequest): CreateOrganizationResponse {
        validateCreateOrganizationRequest()
        val newOrganization = createNewOrganization(createOrganizationRequest)
        userService.assignUserToOrganization(SecurityUtil.currentUserId(), newOrganization.id!!, OrganizationRole.ADMIN)
        return CreateOrganizationResponse().fromEntity(newOrganization)
    }

    private fun createNewOrganization(createOrganizationRequest: CreateOrganizationRequest) =
        organizationRepository.save(createOrganizationRequest.toEntity(Organization::class))

    private fun validateCreateOrganizationRequest() {}
}
