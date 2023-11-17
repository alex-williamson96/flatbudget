package com.caliber.flatbudget.services.impls;

import com.caliber.flatbudget.models.BudgetTable;
import com.caliber.flatbudget.repositories.BudgetRepository;
import com.caliber.flatbudget.repositories.BudgetTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BudgetTableServiceImpl {

    private final BudgetTableRepository budgetTableRepository;

    public BudgetTableServiceImpl(BudgetTableRepository budgetTableRepository,
                                  BudgetRepository budgetRepository) {
        this.budgetTableRepository = budgetTableRepository;
    }

    public BudgetTable save(BudgetTable budgetTable) {
        return budgetTableRepository.save(budgetTable);
    }
}
