package com.ptah.controller.project

import com.ptah.auth.WithAuthority
import com.ptah.dto.project.ProjectDto
import com.ptah.service.project.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/project")
class ProjectController {
    @Autowired
    private val projectService: ProjectService? = null
    @PostMapping
    @WithAuthority("platform-admin")
    fun create(@RequestBody projectDto: ProjectDto?) {
        projectService!!.create(projectDto)
    }

    @PutMapping
    @WithAuthority("project-owner")
    fun update(@RequestBody projectDto: ProjectDto?) {
        projectService!!.create(projectDto)
    }
}
