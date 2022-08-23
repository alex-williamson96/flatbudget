package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.ICategoryService;
import com.caliber.flatbudget.models.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Override
    public Category findById(Long id) {
        return new Category();
    }

    @Override
    public void createCategory(Category category) {

    }
}
