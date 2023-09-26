package com.ptah.entity.project

import com.ptah.common.BaseEntity
import com.ptah.entity.contract.Participant
import com.ptah.entity.userprofiling.ContactInfo
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne

@Entity
class Project : BaseEntity() {
    var name: String? = null

    @OneToOne
    var owner: Participant? = null
    var status: ProjectStatus? = null

    @OneToOne
    var contactInfo: ContactInfo? = null
}
