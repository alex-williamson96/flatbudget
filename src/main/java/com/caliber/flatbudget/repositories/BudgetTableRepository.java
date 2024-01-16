package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.BudgetTable;
import com.caliber.flatbudget.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetTableRepository extends JpaRepository<BudgetTable, Long> {

    List<BudgetTable> findBudgetTablesByBudget(Budget user);

    Optional<BudgetTable> findBudgetTableByYearIsAndMonthIsAndUserIs(String year, String month, User user);
}
