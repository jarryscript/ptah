package com.gihub.jarryzhou.ptah.repository.userprofiling

import com.ptah.entity.userprofiling.Organization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizationRepository : JpaRepository<Organization?, Long?>
