package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.internal.Money;

import java.util.List;

public interface AccountService {

    Account findById(Long id);

    void createAccount(Account account);

    List<Account> findAccountsByBudget(Budget budget);

    void updateWorkingBalance(Long accountId, Money amount);
}
