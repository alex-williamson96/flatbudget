package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.services.AccountServiceImpl;
import com.caliber.flatbudget.services.BudgetServiceImpl;
import com.caliber.flatbudget.services.security.AuthServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@Slf4j
@AllArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;
    private final BudgetServiceImpl budgetService;

    @PreAuthorize("hasAnyRole('USER','SUPER_USER','ADMIN')")
    @GetMapping("/any")
    public String any() {
        return "everyone can access this";
    }



    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> getAllAccounts() {
        User user = new User();

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
        User user = new User();

        System.out.println(account);
        System.out.println(user);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return new ResponseEntity<>(account, HttpStatus.CREATED);



    }


}
