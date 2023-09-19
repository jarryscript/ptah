package com.ptah.entity.userprofiling

import com.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class OrganizationNomination : BaseEntity() {
    @ManyToOne
    var user: User? = null

    @ManyToOne
    var organization: Organization? = null
    var organizationRole: String? = null
}
