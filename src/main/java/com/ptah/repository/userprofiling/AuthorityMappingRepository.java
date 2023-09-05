package com.ptah.repository.userprofiling;

import com.ptah.entity.userprofiling.AuthorityMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface AuthorityMappingRepository extends JpaRepository<AuthorityMapping,Long> {
     @Query("SELECT u FROM AuthorityMapping u WHERE u.role IN :roles")
     Set<AuthorityMapping> findByRoles(Set<String> roles);
}
