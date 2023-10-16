package com.github.jarryzhou.ptah.repository.project

import com.github.jarryzhou.ptah.entity.userprofiling.ProjectNomination
import com.github.jarryzhou.ptah.entity.userprofiling.ProjectRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectNominationRepository : JpaRepository<ProjectNomination?, Long?> {
    fun findByUserId(userId: Long?): List<ProjectNomination?>
    fun findByProjectRoleAndId(projectRole: ProjectRole?, projectId: Long?): List<ProjectNomination?>
}
