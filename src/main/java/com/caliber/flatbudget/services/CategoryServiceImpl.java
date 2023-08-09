package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.repositories.BudgetRepository;
import com.caliber.flatbudget.repositories.CategoryRepository;
import com.caliber.flatbudget.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Category findById(Long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in categoryRepository");
        }
        return categoryRepository.findById(id).get();
    }

    @Override
    public void createCategory(Category category) {
        category.setUpdatedDate(LocalDateTime.now());
        category.setCreatedDate(LocalDateTime.now());
        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteCategory(Category oldCategory, Category newCategory) {
        List<Transaction> transactionList = oldCategory.getTransactionList();

        for (Transaction transaction : transactionList) {
            List<Category> categoryList = transaction.getCategoryList();
            categoryList.remove(oldCategory);
            categoryList.add(newCategory);

            transaction.setUpdatedDate(LocalDateTime.now());
            transaction.setCategoryList(categoryList);
        }

        try {
            transactionRepository.saveAllAndFlush(transactionList);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateCategoryName(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            log.error("Could not find entity " + category.getId() + " in categoryRepository");
        }

        Category _category = categoryRepository.findById(category.getId()).get();

        _category.setUpdatedDate(LocalDateTime.now());
        _category.setName(category.getName());

        try {
            categoryRepository.save(_category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateCategoryOrder(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            log.error("Could not find entity " + category.getId() + " in categoryRepository");
        }

        Category _category = categoryRepository.findById(category.getId()).get();

        _category.setUpdatedDate(LocalDateTime.now());
        _category.setMainOrder(category.getMainOrder());

        try {
            categoryRepository.save(_category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateCategorySubOrder(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            log.error("Could not find entity " + category.getId() + " in categoryRepository");
        }

        Category _category = categoryRepository.findById(category.getId()).get();

        _category.setUpdatedDate(LocalDateTime.now());
        _category.setSubOrder(category.getSubOrder());

        try {
            categoryRepository.save(_category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateCategoryDollarActivity(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            log.error("Could not find entity " + category.getId() + " in categoryRepository");
        }

        Category _category = categoryRepository.findById(category.getId()).get();

        _category.setUpdatedDate(LocalDateTime.now());
        _category.setDollarActivity(category.getDollarActivity());

        try {
            categoryRepository.save(_category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateCategoryCentsActivity(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            log.error("Could not find entity " + category.getId() + " in categoryRepository");
        }

        Category _category = categoryRepository.findById(category.getId()).get();

        _category.setUpdatedDate(LocalDateTime.now());
        _category.setCentsActivity(category.getCentsActivity());

        try {
            categoryRepository.save(_category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateCategoryDollarAssigned(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            log.error("Could not find entity " + category.getId() + " in categoryRepository");
        }

        Category _category = categoryRepository.findById(category.getId()).get();

        _category.setUpdatedDate(LocalDateTime.now());
        _category.setDollarAssigned(category.getDollarAssigned());

        try {
            categoryRepository.save(_category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateCategoryCentsAssigned(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            log.error("Could not find entity " + category.getId() + " in categoryRepository");
        }

        Category _category = categoryRepository.findById(category.getId()).get();

        _category.setUpdatedDate(LocalDateTime.now());
        _category.setCentsAssigned(category.getCentsAssigned());

        try {
            categoryRepository.save(_category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateCategoryDollarAvailable(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            log.error("Could not find entity " + category.getId() + " in categoryRepository");
        }

        Category _category = categoryRepository.findById(category.getId()).get();

        _category.setUpdatedDate(LocalDateTime.now());
        _category.setDollarAvailable(category.getDollarAvailable());

        try {
            categoryRepository.save(_category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateCategoryCentsAvailable(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            log.error("Could not find entity " + category.getId() + " in categoryRepository");
        }

        Category _category = categoryRepository.findById(category.getId()).get();

        _category.setUpdatedDate(LocalDateTime.now());
        _category.setCentsAvailable(category.getCentsAvailable());

        try {
            categoryRepository.save(_category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateCategoryIsCreditCard(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            log.error("Could not find entity " + category.getId() + " in categoryRepository");
        }

        Category _category = categoryRepository.findById(category.getId()).get();

        _category.setUpdatedDate(LocalDateTime.now());
        _category.setIsCreditCard(category.getIsCreditCard());

        try {
            categoryRepository.save(_category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
