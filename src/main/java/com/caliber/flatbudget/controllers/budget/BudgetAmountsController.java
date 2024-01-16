package com.caliber.flatbudget.controllers.budget;

import com.caliber.flatbudget.models.internal.Money;
import com.caliber.flatbudget.services.impls.AccountServiceImpl;
import com.caliber.flatbudget.services.impls.BudgetTableServiceImpl;
import com.caliber.flatbudget.services.impls.CategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/budget/amount")
@Slf4j
@CrossOrigin("*")
public class BudgetAmountsController {

    private final BudgetTableServiceImpl budgetTableService;
    private final AccountServiceImpl accountService;

    public BudgetAmountsController(BudgetTableServiceImpl budgetTableService, AccountServiceImpl accountService) {
        this.budgetTableService = budgetTableService;
        this.accountService = accountService;
    }

    @GetMapping("{budgetYear}/{budgetMonth}")
    public Money getBudgetAssignedTotal(
            Principal principal,
            @PathVariable("budgetYear") String budgetYear,
            @PathVariable("budgetMonth") String budgetMonth) {

        return budgetTableService.getBudgetAmount(principal.getName(), budgetYear, budgetMonth);
    }

    @GetMapping("accounts")
    public Money getBudgetAccountsTotal(
            Principal principal) {

        return accountService.getBudgetAccountsTotal(principal.getName());
    }
}
