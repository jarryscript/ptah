package com.ptah.service.userprofiling

import com.ptah.dto.userprofiling.CreateOrganizationRequest
import com.ptah.dto.userprofiling.CreateOrganizationResponse
import com.ptah.entity.userprofiling.Organization
import com.ptah.repository.userprofiling.OrganizationRepository
import org.springframework.stereotype.Service

@Service
class OrganizationService(private var organizationRepository: OrganizationRepository) {

    fun createOrganization(createOrganizationRequest: CreateOrganizationRequest): CreateOrganizationResponse {
        validateCreateOrganizationRequest()
        return CreateOrganizationResponse().fromEntity(
            organizationRepository.save(
                createOrganizationRequest.toEntity(Organization::class)
            )
        )
    }

    private fun validateCreateOrganizationRequest() {}


}
