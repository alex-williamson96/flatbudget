package com.caliber.flatbudget.dtos.Account;

import com.caliber.flatbudget.dtos.Transaction.TransactionMapper;
import com.caliber.flatbudget.models.Account;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class AccountMapper {
    public AccountDto accountToDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .name(account.getName())
                .dollar(account.getDollar())
                .cents(account.getCents())
                .onBudget(account.getOnBudget())
                .orderPosition(account.getOrderPosition())
                .transactionList(account
                        .getTransactionList()
                        .stream()
                        .map(TransactionMapper::toDto)
                        .collect(toList()))
                .build();
    }

    public AccountOverviewDto accountToOverviewDto(Account account) {
        return AccountOverviewDto.builder()
                .id(account.getId())
                .name(account.getName())
                .dollar(account.getDollar())
                .cents(account.getCents())
                .onBudget(account.getOnBudget())
                .orderPosition(account.getOrderPosition())
                .build();
    }
}
