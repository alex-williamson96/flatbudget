package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.services.impls.BudgetServiceImpl;
import com.caliber.flatbudget.services.impls.BudgetTableServiceImpl;
import com.caliber.flatbudget.services.impls.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@CrossOrigin("*")
public class UserController {

    private final UserServiceImpl userService;
    private final BudgetServiceImpl budgetService;
    private final BudgetTableServiceImpl budgetTableService;

    public UserController(UserServiceImpl userService, BudgetServiceImpl budgetService, BudgetTableServiceImpl budgetTableService) {
        this.userService = userService;
        this.budgetService = budgetService;
        this.budgetTableService = budgetTableService;
    }

    @GetMapping
    public User getUser(Principal principal) {
        return userService.getUser(principal.getName());
    }


}
