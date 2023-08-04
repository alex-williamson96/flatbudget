package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.User;

import java.util.List;

public interface BudgetService {

    Budget findById(Long id);


    void createBudget(Budget budget, User user);

    void updateBudgetName(Budget budget);

    List<Budget> findAllByNameAndUser(User user, String name);
}
