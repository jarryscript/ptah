package com.ptah.entity.userprofiling

import com.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import lombok.Builder
import lombok.Getter
import lombok.Setter

@Entity
@Getter
@Setter
@Builder
class OrganizationNomination : BaseEntity() {
    @ManyToOne
    private val user: User? = null

    @ManyToOne
    private val organization: Organization? = null
    private val organizationRole: String? = null
}
