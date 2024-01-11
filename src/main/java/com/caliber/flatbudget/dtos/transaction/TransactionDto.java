package com.caliber.flatbudget.dtos.transaction;

import com.caliber.flatbudget.dtos.category.CategoryDto;
import com.caliber.flatbudget.dtos.payee.PayeeDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private String name;
    private LocalDate transactionDate;
    private String note;
    private Integer dollar;
    private Integer cents;
    private Boolean isOutflow;
    private Boolean isPending;
    private List<CategoryDto> categoryList;
    private PayeeDto payee;

}
