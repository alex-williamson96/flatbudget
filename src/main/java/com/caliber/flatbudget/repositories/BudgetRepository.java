package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findAllByUserProfile(UserProfile userProfile);

    List<Budget> findAllByNameAndUserProfile(String name, UserProfile user);

    Budget findByNameAndUserProfile(String name, UserProfile userProfile);

}
