package com.caliber.flatbudget.services.impls;

import com.caliber.flatbudget.dtos.account.AccountDto;
import com.caliber.flatbudget.dtos.account.AccountMapper;
import com.caliber.flatbudget.dtos.account.AccountOverviewDto;
import com.caliber.flatbudget.models.*;
import com.caliber.flatbudget.models.internal.Money;
import com.caliber.flatbudget.repositories.AccountRepository;
import com.caliber.flatbudget.repositories.TransactionRepository;
import com.caliber.flatbudget.services.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final BudgetServiceImpl budgetService;
    private final AccountMapper accountMapper;

    @Override
    public AccountDto findById(Long id) {
        if (accountRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in accountRepository");
            return null;
        }

        Account account = accountRepository.findById(id).get();
        return accountMapper.accountToAccountDto(account);
    }

    @Override
    public Account createAccount(Account account, User user) {
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
        Account savedAccount = accountRepository.save(account);

        Transaction startingBalance = Transaction.builder()
                .account(savedAccount)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .budget(budget)
                .user(user)
                .dollar(account.getDollar())
                .cents(account.getCents())
                .isOutflow(account.getDollar() < 0)
                .isPending(false)
                .name("Initial balance")
                .payee(null)
                .transactionDate(LocalDate.now())
                .categoryList(null)
                .build();

        transactionRepository.saveAndFlush(startingBalance);

        return savedAccount;
    }

    @Override
    public List<AccountDto> findAccountsByBudget(Budget budget) {
        return accountRepository.findAccountsByBudget(budget)
                .stream()
                .map((accountMapper::accountToAccountDto))
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountOverviewDto> findAccountOverviewsByBudget(Budget budget) {
        return accountRepository.findAccountsByBudget(budget)
                .stream()
                .map((accountMapper::accountToAccountOverviewDto))
                .collect(Collectors.toList());
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
