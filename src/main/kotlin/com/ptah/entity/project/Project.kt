package com.ptah.entity.project

import com.ptah.common.BaseEntity
import com.ptah.entity.contract.Participant
import com.ptah.repository.userprofiling.ContactInfo
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne

@Entity
class Project : BaseEntity() {
    private val name: String? = null

    @OneToOne
    private val owner: Participant? = null
    private val status: ProjectStatus? = null

    @OneToOne
    private val contactInfo: ContactInfo? = null
}
