package com.github.jarryzhou.ptah.repository.accounting

import com.github.jarryzhou.ptah.entity.accounting.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction?, Long?>
