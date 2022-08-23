package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.ICategoryService;
import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        return new Category();
    }

    @Override
    public void createCategory(Category category) {

    }
}
