package com.ptah.service.project

import com.ptah.dto.project.ProjectDto
import com.ptah.dto.userprofiling.UserDto
import com.ptah.entity.project.Project
import com.ptah.entity.userprofiling.ProjectNomination
import com.ptah.entity.userprofiling.ProjectRole
import com.ptah.entity.userprofiling.User
import com.ptah.repository.project.ProjectNominationRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ProjectService {
    private val projectNominationRepository: ProjectNominationRepository? = null
    fun getProjectsOfUser(userId: Long?): List<ProjectDto?> {
        return Optional.ofNullable(projectNominationRepository!!.findByUserId(userId)).orElseGet { emptyList() }
            .stream().map { obj: ProjectNomination? -> obj.getProject() }
            .map { project: Project? -> ProjectDto().fromEntity(project) }
            .collect(Collectors.toList())
    }

    fun create(projectDto: ProjectDto?) {}
    fun findUsersWithRoleInProject(projectRole: ProjectRole?, projectId: Long?): List<UserDto?> {
        return Optional.ofNullable(projectNominationRepository!!.findByProjectRoleAndId(projectRole, projectId))
            .orElseGet { emptyList() }
            .stream().map { obj: ProjectNomination? -> obj.getUser() }
            .map { user: User? -> UserDto().fromEntity(user) }.toList()
    }
}
