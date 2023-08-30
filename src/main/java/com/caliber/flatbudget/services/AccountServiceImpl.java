package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.models.internal.Money;
import com.caliber.flatbudget.repositories.AccountRepository;
import com.caliber.flatbudget.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserServiceImpl userService;
    private final TransactionRepository transactionRepository;
    private final BudgetServiceImpl budgetService;

    @Override
    public Account findById(Long id) {
        if (accountRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in accountRepository");
        }
        return accountRepository.findById(id).get();
    }

    @Override
    public Account createAccount(Account account) {
        User user = userService.getUser();
        account.setCreatedDate(LocalDateTime.now());
        account.setUpdatedDate(LocalDateTime.now());
        Budget budget;

        if (user.getActiveBudget() == null) {
            budget = budgetService.createDefaultBudget(user);

        } else {
            budget = budgetService.findById(user.getActiveBudget());
        }

        account.setUser(user);
        account.setBudget(budget);
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAccountsByBudget(Budget budget) {
        return accountRepository.findAccountsByBudget(budget);
    }

    @Override
    public void updateWorkingBalance(Long id, Money money) {
        if (accountRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in accountRepository");
        }

        LocalDateTime time = LocalDateTime.now();

        Account account = accountRepository.findById(id).get();

        Money accountMoney = new Money(account.getDollar(), account.getCents());
        accountMoney.subtractMoney(money);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setBudget(account.getBudget());
        transaction.setUser(account.getUser());
        transaction.setUpdatedDate(time);
        transaction.setCreatedDate(time);
        transaction.setTransactionDate(time.toLocalDate());
        transaction.setIsPending(true);
        transaction.setIsOutflow((money.moneyToDouble() < accountMoney.moneyToDouble()));
        transaction.setDollar(Math.abs(accountMoney.getDollar()));
        transaction.setCents(Math.abs(accountMoney.getCents()));
        transaction.setNote("");

        transactionRepository.save(transaction);

        account.setUpdatedDate(time);
        account.setDollar(money.getDollar());
        account.setCents(money.getCents());

        accountRepository.save(account);
    }

    public void updateAccount(Account update) {
        if (accountRepository.findById(update.getId()).isEmpty()) {
            log.error("Could not find entity " + update.getId() + " in accountRepository");
        }

        Account account = accountRepository.findById(update.getId()).get();

        account.setName(update.getName());
        account.setOnBudget(update.getOnBudget());
        account.setOrderPosition(update.getOrderPosition());
        account.setOnBudget(update.getOnBudget());
        account.setUpdatedDate(LocalDateTime.now());

        accountRepository.save(account);
    }


}
