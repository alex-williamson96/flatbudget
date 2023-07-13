package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.IBudgetService;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.UserProfile;
import com.caliber.flatbudget.repositories.BudgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BudgetService implements IBudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

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
        if (budgetRepository.findById(budget.getBudgetId()).isEmpty()) {
            log.error("Could not find entity " + budget.getBudgetId() + " in budgetRepository");
        }

        Budget _budget = budgetRepository.findById(budget.getBudgetId()).get();

        _budget.setName(budget.getName());
        _budget.setUpdatedDate(LocalDateTime.now());

        budgetRepository.save(_budget);
    }

    @Override
    public List<Budget> findAllByNameAndUserProfile(UserProfile userProfile, String name) {
        List<Budget> budgetList = budgetRepository.findAllByNameAndUserProfile(name, userProfile);
        if (budgetList.isEmpty()) {
            return null;
        }

        return budgetList;
    }

    public Budget save(Budget budget) {
        return budgetRepository.save(budget);
    }


}
