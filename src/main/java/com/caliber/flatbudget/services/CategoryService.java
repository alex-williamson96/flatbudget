package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.User;

public interface CategoryService {

    Category findById(Long id);

    Category createCategory(String name, Integer mainOrder, String year, String month, User user);

    void deleteCategory(Category oldCategory, Category newCategory);

    Category updateCategoryName(Long id, String name, User user);

    void updateCategorySubOrder(Category category);

    void updateCategoryOrder(Category category);

    void updateCategoryDollarActivity(Category category);

    void updateCategoryCentsActivity(Category category);

    void updateCategoryDollarAssigned(Category category);

    void updateCategoryCentsAssigned(Category category);

    void updateCategoryDollarAvailable(Category category);

    void updateCategoryCentsAvailable(Category category);

    void updateCategoryIsCreditCard(Category category);

    Category getCategoryByIdAndUser(Long id, User user);
}
