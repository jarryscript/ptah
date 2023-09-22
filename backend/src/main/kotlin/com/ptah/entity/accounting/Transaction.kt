package com.ptah.entity.accounting

import com.ptah.common.BaseEntity
import jakarta.persistence.*
import java.math.BigDecimal


@Entity
class Transaction(account: Account, debit: TransactionDirection, amount: BigDecimal) : BaseEntity() {
    @ManyToOne
    val account: Account? = null

    @Enumerated(EnumType.STRING)
    val direction: TransactionDirection? = null
    val amount: BigDecimal? = null
}
