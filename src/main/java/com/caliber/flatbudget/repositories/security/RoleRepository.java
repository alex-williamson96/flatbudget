package com.caliber.flatbudget.repositories.security;

import com.caliber.flatbudget.models.security.ERole;
import com.caliber.flatbudget.models.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
