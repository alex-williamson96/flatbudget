package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.IBudgetService;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.repositories.BudgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BudgetService implements IBudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    public Budget findById(Long id) {
        if (budgetRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in budgetRepository");
        }

        return budgetRepository.findById(id).get();
    }

    @Override
    public void createBudget(Budget budget) {
        try {
            budgetRepository.save(budget);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
