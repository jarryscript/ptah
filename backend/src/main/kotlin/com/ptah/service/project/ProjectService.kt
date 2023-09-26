package com.ptah.service.project

import com.ptah.dto.project.ProjectDto
import com.ptah.dto.userprofiling.UserDto
import com.ptah.entity.project.Project
import com.ptah.entity.userprofiling.ProjectRole
import com.ptah.repository.project.ProjectNominationRepository
import com.ptah.repository.project.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(var projectNominationRepository: ProjectNominationRepository,
    var projectRepository: ProjectRepository
    ) {
    fun getProjectsOfUser(userId: Long?): List<ProjectDto?> = projectNominationRepository.findByUserId(userId).map {
        ProjectDto("").fromEntity(it?.project)
    }

    fun create(projectDto: ProjectDto) = projectRepository.save(projectDto.toEntity(Project::class))

    fun findUsersWithRoleInProject(projectRole: ProjectRole?, projectId: Long?): List<UserDto?> =
        projectNominationRepository.findByProjectRoleAndId(projectRole, projectId).map {
            UserDto().fromEntity(it?.user)
        }

}
