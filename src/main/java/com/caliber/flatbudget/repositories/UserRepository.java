package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.models.security.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findAllByRoles_Name(ERole role);
}
