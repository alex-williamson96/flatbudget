package com.caliber.flatbudget.dtos.budgetTable;

import com.caliber.flatbudget.dtos.category.CategoryDto;
import com.caliber.flatbudget.models.Budget;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BudgetTableDto {
    private Long id;
    private List<CategoryDto> categoryList;
    private Budget budget;
    private String month;
    private String year;
    private String notes;
}
