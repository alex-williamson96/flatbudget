package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.BudgetTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetTableRepository extends JpaRepository<BudgetTable, Long> {
}
