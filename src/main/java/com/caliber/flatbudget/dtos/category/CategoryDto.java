package com.caliber.flatbudget.dtos.category;

import com.caliber.flatbudget.dtos.transaction.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String name;
    private Integer dollarAssigned;
    private Integer centsAssigned;
    private Integer dollarActivity;
    private Integer centsActivity;
    private Integer dollarAvailable;
    private Integer centsAvailable;
    private Integer mainOrder;
    private Integer subOrder;
    private Boolean isCreditCard;
    private String notes;
    private List<TransactionDto> transactionList;
}
