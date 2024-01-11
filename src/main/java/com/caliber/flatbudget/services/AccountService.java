package com.caliber.flatbudget.services;

import com.caliber.flatbudget.dtos.account.AccountDto;
import com.caliber.flatbudget.dtos.account.AccountOverviewDto;
import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.models.internal.Money;

import java.util.List;

public interface AccountService {

    AccountDto findById(Long id);

    Account createAccount(Account account, User user);

    List<AccountDto> findAccountsByBudget(Budget budget);

    List<AccountOverviewDto> findAccountOverviewsByBudget(Budget budget);

    void updateWorkingBalance(Long accountId, Money amount);
}
