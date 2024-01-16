package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.BudgetTable;
import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<List<Category>> getCategoriesByBudgetTable(BudgetTable budgetTable);

    Optional<Category> getCategoryByIdIsAndUser(Long id, User user);

    Optional<ArrayList<Category>> findCategoriesByBudgetTableIs(BudgetTable budgetTable);

    Optional<Category> findCategoryByUserIsAndIdIs(User user, Long id);

    @Query("SELECT MAX(c.subOrder) FROM Category c " +
            "WHERE c.mainOrder = :mainOrder AND c.budgetTable = :budgetTable")
    Integer findMaxSubOrderForMainOrderAndBudgetTable(
            @Param("mainOrder") Integer mainOrder,
            @Param("budgetTable") BudgetTable budgetTable
    );

}
