package com.ptah.service.project

import com.ptah.dto.project.ProjectDto
import com.ptah.dto.userprofiling.UserDto
import com.ptah.entity.project.Project
import com.ptah.entity.project.ProjectStatus
import com.ptah.entity.userprofiling.OrganizationRole
import com.ptah.entity.userprofiling.ProjectNomination
import com.ptah.entity.userprofiling.ProjectRole
import com.ptah.entity.userprofiling.User
import com.ptah.repository.project.ProjectNominationRepository
import com.ptah.repository.project.ProjectRepository
import com.ptah.util.SecurityUtil
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
