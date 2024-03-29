package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.dtos.account.AccountDto;
import com.caliber.flatbudget.dtos.account.AccountOverviewDto;
import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.services.impls.AccountServiceImpl;
import com.caliber.flatbudget.services.impls.BudgetServiceImpl;
import com.caliber.flatbudget.services.impls.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@Slf4j
@CrossOrigin("*")
public class AccountController {

    private final AccountServiceImpl accountService;
    private final BudgetServiceImpl budgetService;
    private final UserServiceImpl userService;

    public AccountController(AccountServiceImpl accountService, BudgetServiceImpl budgetService, UserServiceImpl userService) {
        this.accountService = accountService;
        this.budgetService = budgetService;
        this.userService = userService;
    }

    @GetMapping("all")
    public List<AccountDto> getAllAccounts(Principal principal) {
        User user = getUser(principal.getName());

        if (user == null) {
            return new ArrayList<>();
        }

        if (user.getActiveBudget() == null) {
            return new ArrayList<>();
        }

        return accountService.findAccountsByBudget(budgetService.findById(user.getActiveBudget()));

    }

    @GetMapping("all/balances")
    public List<AccountOverviewDto> getAllAccountBalances(Principal principal) {
        User user = getUser(principal.getName());

        if (user == null) {
            return new ArrayList<>();
        }

        if (user.getActiveBudget() == null) {
            return new ArrayList<>();
        }

        Long activeBudget = user.getActiveBudget();

        return accountService.findAccountOverviewsByBudget(budgetService.findById(activeBudget));

    }

    @GetMapping("{accountId}")
    public AccountDto getAccountById(@PathVariable("accountId") Long accountId, Principal principal) {
        return accountService.findById(accountId);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account, Principal principal) {
        User user = userService.getUser(principal.getName());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return new ResponseEntity<>(accountService.createAccount(account, user), HttpStatus.CREATED);
    }

    public User getUser(String userName) {
        return userService.getUser(userName);
    }

}
