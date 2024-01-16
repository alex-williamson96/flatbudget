package com.caliber.flatbudget.services.impls;

import com.caliber.flatbudget.models.BudgetTable;
import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.repositories.BudgetRepository;
import com.caliber.flatbudget.repositories.BudgetTableRepository;
import com.caliber.flatbudget.repositories.CategoryRepository;
import com.caliber.flatbudget.repositories.TransactionRepository;
import com.caliber.flatbudget.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private final BudgetTableRepository budgetTableRepository;

    @Override
    public Category findById(Long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in categoryRepository");
        }
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category createCategory(String name, Integer mainOrder, String year, String month, User user) {
        System.out.println("here");
        BudgetTable table = budgetTableRepository.findBudgetTableByYearIsAndMonthIsAndUserIs(year, month, user).orElse(null);

        if (table == null) {
            throw new EntityNotFoundException("Could not find budget table with: " + year + month + user.getUsername());
        }

        Integer maxSubOrder = categoryRepository.findMaxSubOrderForMainOrderAndBudgetTable(mainOrder, table);

        Integer subOrder = maxSubOrder != null ? maxSubOrder + 1 : 0;

        Category category = Category.builder()
                .budgetTable(table)
                .user(user)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .dollarActivity(0)
                .centsActivity(0)
                .dollarAssigned(0)
                .centsAssigned(0)
                .dollarAvailable(0)
                .centsAvailable(0)
                .name(name)
                .mainOrder(mainOrder)
                .subOrder(subOrder)
                .notes("")
                .isCreditCard(false)
                .build();

        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Error saving entity");
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
    public Category updateCategoryName(Long id, String name, User user) {
        Category category = categoryRepository.findCategoryByUserIsAndIdIs(user, id).orElse(null);
        if (category == null) {
            log.error("Could not find entity " + id + " in categoryRepository");
            throw new EntityNotFoundException("Could not find category: " + id);
        }

        category.setUpdatedDate(LocalDateTime.now());
        category.setName(name);

        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
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
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if (optionalCategory.isEmpty()) {
            log.error("Could not find entity " + category.getId() + " in categoryRepository");
            throw new EntityNotFoundException();
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

    @Override
    public Category getCategoryByIdAndUser(Long id, User user) {
        return categoryRepository.getCategoryByIdIsAndUser(id, user).orElse(null);
    }

    public List<Category> getCategoriesByBudgetTable(BudgetTable budgetTable) {
        return categoryRepository.getCategoriesByBudgetTable(budgetTable).orElse(null);
    }

}
