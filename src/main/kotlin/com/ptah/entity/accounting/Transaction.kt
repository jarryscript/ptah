package com.ptah.entity.accounting

import com.ptah.common.BaseEntity
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.math.BigDecimal

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Transaction : BaseEntity() {
    @ManyToOne
    private val account: Account? = null

    @Enumerated(EnumType.STRING)
    private val direction: TransactionDirection? = null
    private val amount: BigDecimal? = null
}
