package com.ptah.service.userprofiling;

import com.ptah.dto.userprofiling.CreateOrganizationRequest;
import com.ptah.entity.userprofiling.Organization;
import com.ptah.repository.userprofiling.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    private OrganizationRepository organizationRepository;
    public void createOrganization(CreateOrganizationRequest createOrganizationRequest){
        validateCreateOrganizationRequest(createOrganizationRequest);
        Organization organization = buildNewOrganization(createOrganizationRequest);
        organizationRepository.save(organization);
    }

    private static Organization buildNewOrganization(CreateOrganizationRequest createOrganizationRequest) {
        Organization organization = createOrganizationRequest.toEntity(Organization.class);
        return organization;
    }

    private void validateCreateOrganizationRequest(CreateOrganizationRequest createOrganizationRequest) {

    }


}
