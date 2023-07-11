package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.UserProfile;
import com.caliber.flatbudget.services.AccountService;
import com.caliber.flatbudget.services.AuthService;
import com.caliber.flatbudget.services.BudgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    private final AuthService authService;

    private final BudgetService budgetService;


    public AccountController(AccountService accountService, AuthService authService, BudgetService budgetService) {
        this.accountService = accountService;
        this.authService = authService;
        this.budgetService = budgetService;
    }


    @GetMapping("all")
    public List<Account> getAllAccounts() {
        UserProfile user = authService.getCurrentUserProfile();

        if (user == null) {
            return new ArrayList<>();
        }

        if (user.getActiveBudget() == null) {
            return new ArrayList<>();
        }

        Long activeBudget = user.getActiveBudget();

        return accountService.findAccountsByBudget(budgetService.findById(activeBudget));

    }

    @PostMapping("create")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        System.out.println("hello createAccount");
        UserProfile user = authService.getCurrentUserProfile();

        System.out.println(account);
        System.out.println(user);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return new ResponseEntity<>(account, HttpStatus.CREATED);



    }


}
