package com.ptah.repository.project

import com.ptah.entity.userprofiling.ProjectNomination
import com.ptah.entity.userprofiling.ProjectRole
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectNominationRepository : JpaRepository<ProjectNomination?, Long?> {
    fun findByUserId(userId: Long?): List<ProjectNomination?>
    fun findByProjectRoleAndId(projectRole: ProjectRole?, projectId: Long?): List<ProjectNomination?>?
}
