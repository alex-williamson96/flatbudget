package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.ICategoryService;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.repositories.BudgetRepository;
import com.caliber.flatbudget.repositories.CategoryRepository;
import com.caliber.flatbudget.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Category findById(Long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in categoryRepository");
        }
        return categoryRepository.findById(id).get();
    }

    @Override
    public void createCategory(Category category) {
        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override 
    public List<Category> findBudgetCategoryList(Long budgetId) {
        if (budgetRepository.findById(budgetId).isEmpty()) {
            log.error("Could not find entity " + budgetId + " in budget Repository");
        }
        Budget budget = budgetRepository.findById(budgetId).get();

        return categoryRepository.findAllByBudget(budget);
    }

    @Override
    public void deleteCategory(Category oldCategory, Category newCategory) {
        List<Transaction> transactionList = oldCategory.getTransactionList();

        for (Transaction transaction : transactionList) {
            List<Category> categoryList = transaction.getCategoryList();
            categoryList.remove(oldCategory);
            categoryList.add(newCategory);

            transaction.setCategoryList(categoryList);
        }
        transactionRepository.saveAllAndFlush(transactionList);
    }
}
