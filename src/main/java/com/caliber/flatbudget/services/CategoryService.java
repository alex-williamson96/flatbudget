package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Category;

public interface CategoryService {

    Category findById(Long id);

    void createCategory(Category category);

    void deleteCategory(Category oldCategory, Category newCategory);

    void updateCategoryName(Category category);

    void updateCategorySubOrder(Category category);

    void updateCategoryOrder(Category category);

    void updateCategoryDollarActivity(Category category);

    void updateCategoryCentsActivity(Category category);

    void updateCategoryDollarAssigned(Category category);

    void updateCategoryCentsAssigned(Category category);

    void updateCategoryDollarAvailable(Category category);

    void updateCategoryCentsAvailable(Category category);

    void updateCategoryIsCreditCard(Category category);
}
