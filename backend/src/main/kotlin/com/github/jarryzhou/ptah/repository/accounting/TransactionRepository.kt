package com.github.jarryzhou.ptah.repository.accounting

import com.ptah.entity.accounting.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction?, Long?>
