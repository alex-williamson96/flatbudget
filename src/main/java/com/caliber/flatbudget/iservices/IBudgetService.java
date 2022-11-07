package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.UserProfile;

public interface IBudgetService {

    Budget findById(Long id);


    void createBudget(Budget budget, UserProfile userProfile);

    void updateBudgetName(Budget budget);
}
