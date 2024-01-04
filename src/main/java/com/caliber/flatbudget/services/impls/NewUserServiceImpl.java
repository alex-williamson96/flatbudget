package com.caliber.flatbudget.services.impls;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.BudgetTable;
import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.repositories.BudgetRepository;
import com.caliber.flatbudget.repositories.BudgetTableRepository;
import com.caliber.flatbudget.repositories.CategoryRepository;
import com.caliber.flatbudget.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class NewUserServiceImpl {

    private final UserRepository userRepository;
    private final BudgetRepository budgetRepository;
    private final BudgetTableRepository budgetTableRepository;
    private final CategoryRepository categoryRepository;

    public NewUserServiceImpl(UserRepository userRepository, BudgetRepository budgetRepository, BudgetTableRepository budgetTableRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
        this.budgetTableRepository = budgetTableRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public User createNewOAuthUser(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedDate(now);
        user.setUpdatedDate(now);
        user.setIsExpired(false);
        user.setIsLocked(false);
        user.setCurrency("USD");

        User savedUser = userRepository.save(user);

        Budget budget = Budget.builder()
                .createdDate(now)
                .updatedDate(now)
                .user(savedUser)
                .name("New Budget")
                .build();

        Budget savedBudget = budgetRepository.saveAndFlush(budget);

        BudgetTable budgetTable = BudgetTable.builder()
                .budget(savedBudget)
                .user(savedUser)
                .year(String.valueOf(LocalDateTime.now().getYear()))
                .month(String.valueOf(LocalDateTime.now().getMonth()))
                .createdDate(now)
                .updatedDate(now)
                .build();

        BudgetTable savedBudgetTable = budgetTableRepository.saveAndFlush(budgetTable);

        int i = 1;
        List<String> homeCategories = Arrays.asList("Home","Rent", "Insurance", "Maintenance");
        buildCategories(homeCategories, savedUser, savedBudgetTable, now, i);
        i++;

        List<String> foodCategories = Arrays.asList("Food","Groceries", "Dining Out", "Fast Food");
        buildCategories(foodCategories, savedUser, savedBudgetTable, now, i);
        i++;

        List<String> carCategories = Arrays.asList("Car","Gas", "Insurance", "Repairs");
        buildCategories(carCategories, savedUser, savedBudgetTable, now, i);
        i++;

        List<String> leisureCategories = Arrays.asList("Leisure","Fun Money", "Hobbies");
        buildCategories(leisureCategories, savedUser, savedBudgetTable, now, i);





        return savedUser;
    }

    public void buildCategories(List<String> categories, User user, BudgetTable budgetTable, LocalDateTime now, int mainOrder) {

        for (int i = 0; i < categories.size(); i++) {
            Category category = Category.builder()
                    .user(user)
                    .budgetTable(budgetTable)
                    .createdDate(now)
                    .updatedDate(now)
                    .mainOrder(mainOrder)
                    .subOrder(i)
                    .name(categories.get(i))
                    .dollarActivity(0)
                    .centsActivity(0)
                    .dollarAvailable(0)
                    .centsAvailable(0)
                    .isCreditCard(false)
                    .build();

            categoryRepository.saveAndFlush(category);

        }


    }

}
