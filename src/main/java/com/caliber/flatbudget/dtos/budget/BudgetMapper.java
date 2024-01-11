package com.caliber.flatbudget.dtos.budget;

import com.caliber.flatbudget.models.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    BudgetMapper INSTANCE = Mappers.getMapper(BudgetMapper.class);

    BudgetDto budgetToBudgetDto(Budget budget);

    Budget budgetDtoToBudget(BudgetDto budgetDto);
}
