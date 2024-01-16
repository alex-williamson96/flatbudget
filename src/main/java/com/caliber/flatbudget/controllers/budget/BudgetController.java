package com.caliber.flatbudget.controllers.budget;

import com.caliber.flatbudget.dtos.budget.BudgetMapper;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.models.internal.Money;
import com.caliber.flatbudget.services.impls.BudgetServiceImpl;
import com.caliber.flatbudget.services.impls.UserServiceImpl;
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
@CrossOrigin("*")
public class BudgetController {
    private final BudgetMapper budgetMapper;

    private final BudgetServiceImpl budgetService;
    private final UserServiceImpl userService;

    public BudgetController(BudgetServiceImpl budgetService, UserServiceImpl userService,
                            BudgetMapper budgetMapper) {
        this.budgetService = budgetService;
        this.userService = userService;
        this.budgetMapper = budgetMapper;
    }


    @GetMapping("active")
    public ResponseEntity<?> getActiveBudget(Principal principal) {
        User user = userService.getUser(principal.getName());

        if (user.getActiveBudget() == null) {
            user.setActiveBudget(userService.setActiveBudget(user));
        }

        return ResponseEntity.ok(budgetMapper.budgetToBudgetDto(budgetService.findById(user.getActiveBudget())));
    }
}
