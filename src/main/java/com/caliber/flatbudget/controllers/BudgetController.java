package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.services.BudgetServiceImpl;
import com.caliber.flatbudget.services.security.AuthServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/budget")
@Slf4j
@AllArgsConstructor
public class BudgetController {

    private final BudgetServiceImpl budgetService;
    private final AuthServiceImpl authService;



    public ResponseEntity<?> getActiveBudget() {
        User user = new User();

        Long budgetId =  user.getActiveBudget();

        return ResponseEntity.ok(budgetService.findById(budgetId));

    }
}
