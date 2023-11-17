package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.services.impls.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/category")
@Slf4j
@AllArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

}
