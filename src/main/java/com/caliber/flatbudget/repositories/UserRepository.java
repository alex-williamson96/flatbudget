package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
