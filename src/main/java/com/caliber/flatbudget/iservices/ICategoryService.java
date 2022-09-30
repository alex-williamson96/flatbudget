package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.Category;

import java.util.List;

public interface ICategoryService {

    Category findById(Long id);

    void createCategory(Category category);

    List<Category> findBudgetCategoryList(Long budgetId);

    void deleteCategory(Category oldCategory, Category newCategory);

}
