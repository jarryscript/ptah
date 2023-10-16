package com.github.jarryzhou.ptah.entity.accounting

import com.github.jarryzhou.ptah.common.BaseEntity
import com.github.jarryzhou.ptah.entity.contract.Participant
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import java.math.BigDecimal

@Entity
class Account(
    var name: String? = null,
    var balance: BigDecimal? = null,
    @OneToOne
    var owner: Participant? = null
) : BaseEntity()
