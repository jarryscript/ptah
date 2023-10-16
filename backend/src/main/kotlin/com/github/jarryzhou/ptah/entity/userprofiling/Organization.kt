package com.github.jarryzhou.ptah.entity.userprofiling

import com.github.jarryzhou.ptah.entity.contract.Participant
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne

@Entity
class Organization : Participant() {
    var businessCode: String? = null

    @OneToOne
    var contactInfo: ContactInfo? = null
}
