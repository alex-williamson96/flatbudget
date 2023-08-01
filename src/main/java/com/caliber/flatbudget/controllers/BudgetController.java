package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.user.UserProfile;
import com.caliber.flatbudget.services.AuthServiceImpl;
import com.caliber.flatbudget.services.BudgetServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/budget")
@Slf4j
public class BudgetController {

    private final BudgetServiceImpl budgetService;

    private final AuthServiceImpl authService;


    public BudgetController(BudgetServiceImpl budgetServiceImpl, AuthServiceImpl authServiceImpl) {
        this.budgetService = budgetServiceImpl;
        this.authService = authServiceImpl;
    }

    public ResponseEntity<?> getActiveBudget() {
        UserProfile user = authService.getCurrentUserProfile();

        Long budgetId =  user.getActiveBudget();

        return ResponseEntity.ok(budgetService.findById(budgetId));

    }
}
