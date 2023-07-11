package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.UserProfile;
import com.caliber.flatbudget.services.AuthService;
import com.caliber.flatbudget.services.BudgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/budget")
@Slf4j
public class BudgetController {

    private final BudgetService budgetService;

    private final AuthService authService;


    public BudgetController(BudgetService budgetService, AuthService authService) {
        this.budgetService = budgetService;
        this.authService = authService;
    }

    public ResponseEntity<?> getActiveBudget() {
        UserProfile user = authService.getCurrentUserProfile();

        Long budgetId =  user.getActiveBudget();

        return ResponseEntity.ok(budgetService.findById(budgetId));

    }
}
