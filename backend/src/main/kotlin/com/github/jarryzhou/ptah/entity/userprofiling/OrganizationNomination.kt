package com.github.jarryzhou.ptah.entity.userprofiling

import com.github.jarryzhou.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.ManyToOne

@Entity
class OrganizationNomination(
    @ManyToOne var user: User? = null,
    @ManyToOne var organization: Organization? = null,
    @Enumerated(EnumType.STRING) var organizationRole: OrganizationRole? = null
) : BaseEntity()
