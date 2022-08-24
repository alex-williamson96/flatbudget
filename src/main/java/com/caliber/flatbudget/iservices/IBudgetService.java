package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.Budget;

public interface IBudgetService {

    Budget findById(Long id);

    void createBudget(Budget budget);


}
