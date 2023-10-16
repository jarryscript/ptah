package com.github.jarryzhou.ptah.entity.userprofiling

import com.github.jarryzhou.ptah.common.BaseEntity
import jakarta.persistence.Entity

@Entity
class ContactInfo : BaseEntity() {
    var name: String? = null
    var address1: String? = null
    var address2: String? = null
    var city: String? = null
    var province: String? = null
    var postalCode: String? = null
    var phoneNumber: String? = null
}
