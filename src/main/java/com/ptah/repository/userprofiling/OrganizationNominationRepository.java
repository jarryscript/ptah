package com.ptah.repository.userprofiling;

import com.ptah.entity.userprofiling.OrganizationNomination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationNominationRepository extends JpaRepository<OrganizationNomination, Long> {
    List<OrganizationNomination> findByUserId(Long userId);
}
