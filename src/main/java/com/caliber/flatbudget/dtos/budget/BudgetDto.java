package com.caliber.flatbudget.dtos.budget;

import com.caliber.flatbudget.dtos.budgetTable.BudgetTableDto;
import com.caliber.flatbudget.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BudgetDto {
    private Long id;
    private String name;
    private User user;
    private List<BudgetTableDto> budgetTableList;
}
