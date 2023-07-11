package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.Category;

import java.util.List;

public interface ICategoryService {

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
