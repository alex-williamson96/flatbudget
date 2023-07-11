package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.BudgetTable;
import com.caliber.flatbudget.models.UserProfile;
import com.caliber.flatbudget.services.AuthService;
import com.caliber.flatbudget.services.BudgetService;
import com.caliber.flatbudget.services.BudgetTableService;
import com.caliber.flatbudget.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    private final UserService userService;

    private final AuthService authService;

    private final BudgetService budgetService;

    private final BudgetTableService budgetTableService;

    public UserController(UserService userService, AuthService authService, BudgetService budgetService, BudgetTableService budgetTableService) {
        this.userService = userService;
        this.authService = authService;
        this.budgetService = budgetService;
        this.budgetTableService = budgetTableService;
    }

    @GetMapping
    public UserProfile getUserInfo() {
        UserProfile user = authService.getCurrentUserProfile();


        if (user == null) {
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

            newUser.setActiveBudget(budgetService.save(budget).getId());

            budgetTableService.save(budgetTable);

            return this.userService.save(newUser);
        }

        return user;
    }
}
