package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.repositories.BudgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository) {
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
    public void createBudget(Budget budget, User user) {
        budget.setCreatedDate(LocalDateTime.now());
        budget.setCreatedDate(LocalDateTime.now());
        budget.setUser(user);

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
    public List<Budget> findAllByNameAndUser(User user, String name) {
        List<Budget> budgetList = budgetRepository.findAllByNameAndUser(name, user);
        if (budgetList.isEmpty()) {
            return null;
        }

        return budgetList;
    }

    public Budget save(Budget budget) {
        return budgetRepository.save(budget);
    }


}
