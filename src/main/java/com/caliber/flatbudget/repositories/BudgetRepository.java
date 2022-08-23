package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
