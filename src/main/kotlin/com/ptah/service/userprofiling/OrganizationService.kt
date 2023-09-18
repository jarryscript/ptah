package com.ptah.service.userprofiling

import com.ptah.dto.userprofiling.CreateOrganizationRequest
import com.ptah.entity.userprofiling.Organization
import com.ptah.repository.userprofiling.OrganizationRepository
import org.springframework.stereotype.Service

@Service
class OrganizationService {
    private val organizationRepository: OrganizationRepository? = null
    fun createOrganization(createOrganizationRequest: CreateOrganizationRequest) {
        validateCreateOrganizationRequest(createOrganizationRequest)
        val organization = buildNewOrganization(createOrganizationRequest)
        organizationRepository!!.save(organization)
    }

    private fun validateCreateOrganizationRequest(createOrganizationRequest: CreateOrganizationRequest) {}

    companion object {
        private fun buildNewOrganization(createOrganizationRequest: CreateOrganizationRequest): Organization? {
            return createOrganizationRequest.toEntity(Organization::class.java)
        }
    }
}
