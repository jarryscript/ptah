package com.github.jarryzhou.ptah.repository.userprofiling

import com.github.jarryzhou.ptah.entity.userprofiling.Organization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizationRepository : JpaRepository<Organization?, Long?>
