package com.caliber.flatbudget.dtos.budgetTable;

import com.caliber.flatbudget.dtos.budget.BudgetDto;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.BudgetTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface BudgetTableMapper {

    BudgetTableMapper INSTANCE = Mappers.getMapper(BudgetTableMapper.class);

    BudgetTableDto budgetTableToBudgetTableDto(BudgetTable budgetTable);

    BudgetTable budgetTableDtoToBudget(BudgetTableDto budgetTableDto);
}
