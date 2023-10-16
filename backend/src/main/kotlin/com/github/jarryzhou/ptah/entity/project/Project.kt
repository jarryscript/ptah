package com.github.jarryzhou.ptah.entity.project

import com.github.jarryzhou.ptah.common.BaseEntity
import com.github.jarryzhou.ptah.entity.contract.Participant
import com.github.jarryzhou.ptah.entity.userprofiling.ContactInfo
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.OneToOne

@Entity
class Project : BaseEntity() {
    var name: String? = null

    @OneToOne
    var owner: Participant? = null

    @Enumerated(EnumType.STRING)
    var status: ProjectStatus? = null

    @OneToOne
    var contactInfo: ContactInfo? = null
}
