package com.ptah.service.project

import com.ptah.dto.project.ProjectDto
import com.ptah.dto.userprofiling.UserDto
import com.ptah.entity.project.Project
import com.ptah.entity.userprofiling.ProjectNomination
import com.ptah.entity.userprofiling.ProjectRole
import com.ptah.entity.userprofiling.User
import com.ptah.repository.project.ProjectNominationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ProjectService {
    @Autowired
    lateinit var projectNominationRepository: ProjectNominationRepository
    fun getProjectsOfUser(userId: Long?): List<ProjectDto?> = projectNominationRepository.findByUserId(userId).map {
        ProjectDto().fromEntity(it?.project)
    }

    fun create(projectDto: ProjectDto?) {}

    fun findUsersWithRoleInProject(projectRole: ProjectRole?, projectId: Long?): List<UserDto?> =
        projectNominationRepository.findByProjectRoleAndId(projectRole, projectId).map {
            UserDto().fromEntity(it?.user)
        }

}
