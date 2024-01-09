package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.BudgetTable;
import com.caliber.flatbudget.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<List<Category>> getCategoriesByBudgetTable(BudgetTable budgetTable);

}
