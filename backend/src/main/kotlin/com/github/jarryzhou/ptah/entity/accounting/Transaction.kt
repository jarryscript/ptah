package com.github.jarryzhou.ptah.entity.accounting

import com.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
class Transaction(account: Account, debit: TransactionDirection, amount: BigDecimal) : BaseEntity() {
    @ManyToOne
    val account: Account? = null

    @Enumerated(EnumType.STRING)
    val direction: TransactionDirection? = null
    val amount: BigDecimal? = null
}
