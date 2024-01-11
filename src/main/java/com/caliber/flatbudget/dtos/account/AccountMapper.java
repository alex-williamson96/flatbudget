package com.caliber.flatbudget.dtos.account;

import com.caliber.flatbudget.dtos.transaction.TransactionMapper;
import com.caliber.flatbudget.models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto accountToAccountDto(Account account);

    AccountOverviewDto accountToAccountOverviewDto(Account account);

    Account accountDtoToAccount(AccountDto accountDto);

    Account accountOverviewDtoToAccount(AccountOverviewDto accountOverviewDto);
}
