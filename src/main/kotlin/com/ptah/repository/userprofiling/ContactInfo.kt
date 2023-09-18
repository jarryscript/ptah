package com.ptah.repository.userprofiling

import com.ptah.common.BaseEntity
import jakarta.persistence.Entity
import lombok.Getter
import lombok.Setter

@Entity
@Getter
@Setter
class ContactInfo : BaseEntity() {
    private val name: String? = null
    private val address1: String? = null
    private val address2: String? = null
    private val city: String? = null
    private val province: String? = null
    private val postalCode: String? = null
    private val phoneNumber: String? = null
}
