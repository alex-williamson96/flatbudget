package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByEmail(String email);
}
