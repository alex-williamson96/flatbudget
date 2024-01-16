package com.caliber.flatbudget.services.impls;

import com.caliber.flatbudget.models.BudgetTable;
import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.models.internal.Money;
import com.caliber.flatbudget.repositories.BudgetRepository;
import com.caliber.flatbudget.repositories.BudgetTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;

@Service
@Slf4j
public class BudgetTableServiceImpl {

    private final BudgetTableRepository budgetTableRepository;
    private final UserServiceImpl userService;
    private final CategoryServiceImpl categoryService;


    public BudgetTableServiceImpl(BudgetTableRepository budgetTableRepository,
                                  BudgetRepository budgetRepository, UserServiceImpl userService, CategoryServiceImpl categoryService) {
        this.budgetTableRepository = budgetTableRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public BudgetTable save(BudgetTable budgetTable) {
        return budgetTableRepository.save(budgetTable);
    }

    public Money getBudgetAmount(String username, String year, String month) {
        User user = userService.getUser(username);

        BudgetTable budgetTable = budgetTableRepository.findBudgetTableByYearIsAndMonthIsAndUserIs(year, month, user).orElse(null);

        if (budgetTable == null) {
            return null;
        }

        List<Category> categoryList = categoryService.getCategoriesByBudgetTable(budgetTable);

        return new Money(
                categoryList.stream()
                        .mapToInt(Category::getDollarAssigned)
                        .sum(),
                categoryList.stream()
                        .mapToInt(Category::getCentsAssigned)
                        .sum()
        );
    }

}
