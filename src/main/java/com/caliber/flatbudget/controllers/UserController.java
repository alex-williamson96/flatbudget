package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.security.services.UserDetailsImpl;
import com.caliber.flatbudget.services.BudgetServiceImpl;
import com.caliber.flatbudget.services.BudgetTableServiceImpl;
import com.caliber.flatbudget.services.UserServiceImpl;
import com.sun.security.auth.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final BudgetServiceImpl budgetService;
    private final BudgetTableServiceImpl budgetTableService;

    @GetMapping
    public User getUser() {
        return userService.getUser();
    }


}
