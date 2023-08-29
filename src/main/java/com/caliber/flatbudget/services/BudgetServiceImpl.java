package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.repositories.BudgetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserServiceImpl userService;

    @Override
    public Budget findById(Long id) {
        if (budgetRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in budgetRepository");
        }

        return budgetRepository.findById(id).get();
    }

    @Override
    public Budget createBudget(Budget budget, User user) {
        budget.setCreatedDate(LocalDateTime.now());
        budget.setCreatedDate(LocalDateTime.now());
        budget.setUser(user);

        try {
            budgetRepository.save(budget);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        Budget savedBudget = save(budget);

        if (user.getActiveBudget() == null) {
            user.setActiveBudget(savedBudget.getBudgetId());
            userService.saveUser(user);
        }


        return savedBudget;
    }

    @Override
    public void updateBudgetName(Budget budget) {
        if (budgetRepository.findById(budget.getBudgetId()).isEmpty()) {
            log.error("Could not find entity " + budget.getBudgetId() + " in budgetRepository");
        }

        Budget _budget = budgetRepository.findById(budget.getBudgetId()).get();

        _budget.setName(budget.getName());
        _budget.setUpdatedDate(LocalDateTime.now());

        save(_budget);
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

    public Budget createDefaultBudget(User user) {
        LocalDateTime now = LocalDateTime.now();
        Budget budget = Budget.builder()
                .name("Budget Name")
                .user(user)
                .updatedDate(now)
                .createdDate(now)
                .build();

        budget = budgetRepository.save()
        Category category1 = Category.builder()
                .budgetTable()
                .build();
    }
}
