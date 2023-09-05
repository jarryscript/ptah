package com.ptah.repository.userprofiling;

import com.ptah.entity.userprofiling.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
