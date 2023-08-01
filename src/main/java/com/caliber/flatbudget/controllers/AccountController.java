package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.user.UserProfile;
import com.caliber.flatbudget.services.AccountServiceImpl;
import com.caliber.flatbudget.services.AuthServiceImpl;
import com.caliber.flatbudget.services.BudgetServiceImpl;
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

    private final AccountServiceImpl accountService;

    private final AuthServiceImpl authService;

    private final BudgetServiceImpl budgetService;


    public AccountController(AccountServiceImpl accountServiceImpl, AuthServiceImpl authServiceImpl, BudgetServiceImpl budgetServiceImpl) {
        this.accountService = accountServiceImpl;
        this.authService = authServiceImpl;
        this.budgetService = budgetServiceImpl;
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
