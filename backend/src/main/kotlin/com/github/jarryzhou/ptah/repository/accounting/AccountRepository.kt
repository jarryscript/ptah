package com.github.jarryzhou.ptah.repository.accounting

import com.ptah.entity.accounting.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account?, Long?> {
    fun findByOwnerId(ownerId: Long): Account
}
