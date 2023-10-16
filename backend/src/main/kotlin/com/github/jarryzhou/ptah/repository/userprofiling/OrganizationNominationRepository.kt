package com.github.jarryzhou.ptah.repository.userprofiling

import com.github.jarryzhou.ptah.entity.userprofiling.OrganizationNomination
import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationNominationRepository : JpaRepository<OrganizationNomination?, Long?> {
    fun findByUserId(userId: Long?): List<OrganizationNomination?>
    fun findByUserIdAndOrganizationId(userId: Long, organizationId: Long): OrganizationNomination?
}
