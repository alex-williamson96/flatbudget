package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.Category;

public interface ICategoryService {

    Category findById(Long id);

    void createCategory(Category category);
}
