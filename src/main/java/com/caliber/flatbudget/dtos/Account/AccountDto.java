package com.caliber.flatbudget.dtos.Account;

import com.caliber.flatbudget.dtos.Transaction.TransactionDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class AccountDto {

    private Long id;
    private String name;
    private Integer dollar;
    private Integer cents;
    private Boolean onBudget;
    private Integer orderPosition;
    private List<TransactionDto> transactionList;
//    private UserDto user;
//    private BudgetDto budget;

}
