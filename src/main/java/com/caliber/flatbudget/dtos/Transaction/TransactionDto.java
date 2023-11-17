package com.caliber.flatbudget.dtos.Transaction;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {

    private Long id;
    private String name;
    private LocalDate transactionDate;
    private String note;
    private Integer dollar;
    private Integer cents;
    private Boolean isOutflow;
    private Boolean isPending;
//    private AccountDto account;
//    private UserDto user;
//    private List<CategoryDto> categoryList;
//    private PayeeDto payee;

}
