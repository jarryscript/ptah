package com.github.jarryzhou.ptah.service.project

import com.github.jarryzhou.ptah.dto.project.ProjectDto
import com.github.jarryzhou.ptah.dto.userprofiling.UserDto
import com.github.jarryzhou.ptah.entity.project.Project
import com.github.jarryzhou.ptah.entity.project.ProjectStatus
import com.github.jarryzhou.ptah.entity.userprofiling.ProjectNomination
import com.github.jarryzhou.ptah.entity.userprofiling.ProjectRole
import com.github.jarryzhou.ptah.entity.userprofiling.User
import com.github.jarryzhou.ptah.repository.project.ProjectNominationRepository
import com.github.jarryzhou.ptah.repository.project.ProjectRepository
import com.github.jarryzhou.ptah.util.SecurityUtil
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private var projectNominationRepository: ProjectNominationRepository,
    private var projectRepository: ProjectRepository
) {
    fun getProjectsOfUser(userId: Long?): List<ProjectDto?> = projectNominationRepository.findByUserId(userId).map {
        ProjectDto(name = "").fromEntity(it?.project)
    }

    fun create(projectDto: ProjectDto) {
        val newProject = createNewProject(projectDto)
        assignUserToProject(SecurityUtil.currentUserId(), newProject.id!!, projectRole = ProjectRole.PROJECT_MANAGER)
    }

    private fun createNewProject(projectDto: ProjectDto) =
        projectRepository.save(projectDto.toEntity(Project::class).apply { status = ProjectStatus.PENDING })

    fun findUsersWithRoleInProject(projectRole: ProjectRole?, projectId: Long?): List<UserDto?> =
        projectNominationRepository.findByProjectRoleAndId(projectRole, projectId).map {
            UserDto().fromEntity(it?.user)
        }

    fun assignUserToProject(userId: Long, projectId: Long, projectRole: ProjectRole) {
        projectNominationRepository.save(
            ProjectNomination(
                user = User().apply { id = userId },
                project = Project().apply { id = projectId },
                projectRole = projectRole
            )
        )
    }
}
