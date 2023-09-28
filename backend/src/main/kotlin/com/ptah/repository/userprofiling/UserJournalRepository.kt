package com.ptah.repository.userprofiling

import com.ptah.entity.userprofiling.UserJournal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJournalRepository : JpaRepository<UserJournal?, Long?> {
    fun findByUserId(userId: Long): List<UserJournal>?
}
