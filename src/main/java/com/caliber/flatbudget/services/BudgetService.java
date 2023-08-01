package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.user.UserProfile;

import java.util.List;

public interface BudgetService {

    Budget findById(Long id);


    void createBudget(Budget budget, UserProfile userProfile);

    void updateBudgetName(Budget budget);

    List<Budget> findAllByNameAndUserProfile(UserProfile userProfile, String name);
}
