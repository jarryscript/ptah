package com.ptah.repository.userprofiling

import com.ptah.entity.userprofiling.AuthorityMapping
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AuthorityMappingRepository : JpaRepository<AuthorityMapping?, Long?> {
    @Query("SELECT u FROM AuthorityMapping u WHERE u.role IN :roles")
    fun findByRoles(roles: Set<String?>?): Set<AuthorityMapping?>
    fun findByRole(role: String): AuthorityMapping?
}
