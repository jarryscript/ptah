package com.ptah.entity.contract

import com.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class Participant : BaseEntity() {
    val name: String? = null
}
