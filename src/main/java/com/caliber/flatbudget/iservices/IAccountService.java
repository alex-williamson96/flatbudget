package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.UserProfile;

import java.util.List;

public interface IAccountService {

    Account findById(Long id);

    void createAccount(Account account);

    List<Account> findAccountsByUserAndBudget(UserProfile userProfile, Budget budget);
}
