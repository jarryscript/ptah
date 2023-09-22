package com.ptah.repository.userprofiling

import com.ptah.entity.userprofiling.OrganizationNomination
import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationNominationRepository : JpaRepository<OrganizationNomination?, Long?> {
    fun findByUserId(userId: Long?): List<OrganizationNomination?>
}