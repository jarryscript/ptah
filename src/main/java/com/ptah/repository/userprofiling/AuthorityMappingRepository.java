package com.ptah.repository.userprofiling;

import com.ptah.entity.userprofiling.AuthorityMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AuthorityMappingRepository extends JpaRepository<AuthorityMapping,Long> {
     Set<AuthorityMapping> findByRoles(Set<String> roles);
}
