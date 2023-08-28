package com.ptah.repository.project;

import com.ptah.entity.userprofiling.ProjectNomination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectNominationRepository extends JpaRepository<ProjectNomination,Long> {
    List<ProjectNomination> findByUserId(Long userId);
}
