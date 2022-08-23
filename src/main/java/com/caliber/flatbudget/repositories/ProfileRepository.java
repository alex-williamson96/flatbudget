package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
