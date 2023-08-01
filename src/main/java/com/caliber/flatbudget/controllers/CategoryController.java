package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.services.CategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/category")
@Slf4j
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryService = categoryServiceImpl;
    }
}
