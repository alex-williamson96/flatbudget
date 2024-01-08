package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.services.impls.BudgetServiceImpl;
import com.caliber.flatbudget.services.impls.BudgetTableServiceImpl;
import com.caliber.flatbudget.services.impls.UserServiceImpl;
import com.caliber.flatbudget.services.security.AuthServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/budget")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BudgetController {

    private final BudgetServiceImpl budgetService;
    private final UserServiceImpl userService;



    @GetMapping("active")
    public ResponseEntity<?> getActiveBudget(Principal principal) {
        User user = userService.getUser(principal.getName());

        if (user.getActiveBudget() == null) {
            user.setActiveBudget(userService.setActiveBudget(user));
        }

        return ResponseEntity.ok(budgetService.findById(user.getActiveBudget()));
    }
}
