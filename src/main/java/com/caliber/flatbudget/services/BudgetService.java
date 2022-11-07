package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.IBudgetService;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.UserProfile;
import com.caliber.flatbudget.repositories.BudgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public void createBudget(Budget budget, UserProfile userProfile) {
        budget.setCreatedDate(LocalDateTime.now());
        budget.setCreatedDate(LocalDateTime.now());
        budget.setUserProfile(userProfile);

        try {
            budgetRepository.save(budget);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateBudgetName(Budget budget) {
        if (budgetRepository.findById(budget.getId()).isEmpty()) {
            log.error("Could not find entity " + budget.getId() + " in budgetRepository");
        }

        Budget _budget = budgetRepository.findById(budget.getId()).get();

        _budget.setName(budget.getName());
        _budget.setUpdatedDate(LocalDateTime.now());
    }


}
