package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.IAccountService;
import com.caliber.flatbudget.models.*;
import com.caliber.flatbudget.repositories.AccountRepository;
import com.caliber.flatbudget.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Account findById(Long id) {
        if (accountRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in accountRepository");
        }
        return accountRepository.findById(id).get();
    }

    @Override
    public void createAccount(Account account) {
        try {
            accountRepository.save(account);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Account> findAccountsByUserAndBudget(UserProfile userProfile, Budget budget) {
        return accountRepository.findAccountsByUserProfileAndBudget(userProfile, budget);
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
        transaction.setUserProfile(account.getUserProfile());
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


}
