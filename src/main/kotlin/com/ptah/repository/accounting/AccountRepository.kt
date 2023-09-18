package com.ptah.repository.accounting

import com.ptah.entity.accounting.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account?, Long?>
