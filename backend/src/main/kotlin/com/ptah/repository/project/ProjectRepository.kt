package com.ptah.repository.project

import com.ptah.entity.project.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project?, Long?> {
}
