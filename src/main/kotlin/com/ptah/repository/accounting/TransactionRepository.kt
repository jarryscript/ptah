package com.ptah.repository.accounting

import com.ptah.entity.accounting.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction?, Long?>
