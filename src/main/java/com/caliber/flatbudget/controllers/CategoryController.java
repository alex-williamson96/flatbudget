package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.services.impls.CategoryServiceImpl;
import com.caliber.flatbudget.services.impls.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/category")
@Slf4j
@CrossOrigin("*")
public class CategoryController {

    private final CategoryServiceImpl categoryService;
    private final UserServiceImpl userService;

    public CategoryController(CategoryServiceImpl categoryService, UserServiceImpl userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @PutMapping("{id}/assigned")
    public ResponseEntity<?> updateCategoryAssignedValues(@RequestBody Category category, Principal principal, @PathVariable("id") Long id) {
        if (!Objects.equals(id, category.getId())) {
            return ResponseEntity.badRequest().build();
        }
        User user = userService.getUser(principal.getName());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        categoryService.updateCategoryDollarAssigned(category);
        categoryService.updateCategoryCentsAssigned(category);

        return ResponseEntity.ok().build();
    }

}
