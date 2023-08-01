package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.BudgetTable;
import com.caliber.flatbudget.models.user.UserProfile;
import com.caliber.flatbudget.services.AuthServiceImpl;
import com.caliber.flatbudget.services.BudgetServiceImpl;
import com.caliber.flatbudget.services.BudgetTableServiceImpl;
import com.caliber.flatbudget.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    private final AuthServiceImpl authService;

    private final BudgetServiceImpl budgetService;

    private final BudgetTableServiceImpl budgetTableService;

    public UserController(UserServiceImpl userServiceImpl, AuthServiceImpl authServiceImpl, BudgetServiceImpl budgetServiceImpl, BudgetTableServiceImpl budgetTableServiceImpl) {
        this.userService = userServiceImpl;
        this.authService = authServiceImpl;
        this.budgetService = budgetServiceImpl;
        this.budgetTableService = budgetTableServiceImpl;
    }

    @GetMapping
    public Object getUserInfo() {
        UserProfile user = authService.getCurrentUserProfile();
        System.out.println(SecurityContextHolder.getContext());

        if (user == null) {
            return SecurityContextHolder.getContext();
        }

        if (user.equals(new UserProfile())) {
            DefaultOidcUser principal = authService.getPrincipal();

            LocalDateTime now = LocalDateTime.now();

            UserProfile newUser = this.userService.createUser(UserProfile
                    .builder()
                    .email(principal.getEmail())
                    .firstName(principal.getGivenName())
                    .lastName(principal.getFamilyName())
                    .createdDate(now)
                    .updatedDate(now)
                    .currency("USD")
                    .currencyFormat("before")
                    .dateFormat("MM.DD.YYYY")
                    .build());

            Budget budget = Budget.builder()
                    .name("My first budget")
                    .userProfile(newUser)
                    .createdDate(now)
                    .updatedDate(now)
                    .build();

            BudgetTable budgetTable = BudgetTable.builder()
                    .month(LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0, 0))
                    .budget(budget)
                    .createdDate(now)
                    .updatedDate(now)
                    .notes("")
                    .user(newUser)
                    .build();

            Budget newBudget = budgetService.save(budget);

            newUser.setActiveBudget(newBudget.getBudgetId());
            newUser.setBudgetList(Collections.singletonList(newBudget));

            budgetTableService.save(budgetTable);

            return this.userService.save(newUser);
        }

        return user;
    }
}
