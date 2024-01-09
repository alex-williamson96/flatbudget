package com.caliber.flatbudget.services.impls;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.BudgetTable;
import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.repositories.BudgetRepository;
import com.caliber.flatbudget.repositories.BudgetTableRepository;
import com.caliber.flatbudget.repositories.CategoryRepository;
import com.caliber.flatbudget.services.BudgetService;
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
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserServiceImpl userService;
    private final BudgetTableRepository tableRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Budget findById(Long id) {
        Optional<Budget> optionalBudget = budgetRepository.findBudgetByBudgetId(id);

        if (optionalBudget.isEmpty()) {
            log.error("Could not find entity " + id + " in budgetRepository");
        }

        Budget budget = optionalBudget.get();


        budget.setBudgetTableList(tableRepository.findBudgetTablesByBudget(budget));

        for (BudgetTable budgetTable : budget.getBudgetTableList()) {
            budgetTable.setCategoryList(categoryRepository.getCategoriesByBudgetTable(budgetTable).orElse(null));
        }

        return budget;
    }

    @Override
    public Budget createBudget(Budget budget, User user) {
        budget.setCreatedDate(LocalDateTime.now());
        budget.setCreatedDate(LocalDateTime.now());
        budget.setUser(user);

        try {
            budgetRepository.save(budget);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        Budget savedBudget = save(budget);

        if (user.getActiveBudget() == null) {
            user.setActiveBudget(savedBudget.getBudgetId());
            userService.saveUser(user);
        }


        return savedBudget;
    }

    @Override
    public void updateBudgetName(Budget budget) {
        if (budgetRepository.findById(budget.getBudgetId()).isEmpty()) {
            log.error("Could not find entity " + budget.getBudgetId() + " in budgetRepository");
        }

        Budget _budget = budgetRepository.findById(budget.getBudgetId()).get();

        _budget.setName(budget.getName());
        _budget.setUpdatedDate(LocalDateTime.now());

        save(_budget);
    }

    @Override
    public List<Budget> findAllByNameAndUser(User user, String name) {
        List<Budget> budgetList = budgetRepository.findAllByNameAndUser(name, user);
        if (budgetList.isEmpty()) {
            return null;
        }

        return budgetList;
    }

    public Budget save(Budget budget) {
        return budgetRepository.save(budget);
    }

    public Budget createDefaultBudget(User user) {
        LocalDateTime now = LocalDateTime.now();
        Budget budget = Budget.builder()
                .name("Budget Name")
                .user(user)
                .updatedDate(now)
                .createdDate(now)
                .build();

        budget = budgetRepository.save(budget);

        user.setActiveBudget(budget.getBudgetId());
        userService.saveUser(user);

        BudgetTable budgetTable = BudgetTable.builder()
                .budget(budget)
                .month(LocalDateTime.now().getMonth().toString())
                .year(String.valueOf(LocalDateTime.now().getYear()))
                .updatedDate(now)
                .createdDate(now)
                .user(user)
                .build();

        tableRepository.save(budgetTable);

        List<Category> categoryList = new ArrayList<>();

        Category home = createBlankCategory("Home", now, budgetTable, user);
        categoryList.add(home);
        Category rent = createBlankCategory("Rent", now, budgetTable, user);
        Category utilities = createBlankCategory("Utilities", now, budgetTable, user);
        Category homeInsurance = createBlankCategory("Insurance", now, budgetTable, user);
        rent.setSubOrder(1);
        categoryList.add(rent);
        utilities.setSubOrder(2);
        categoryList.add(utilities);
        homeInsurance.setSubOrder(3);
        categoryList.add(homeInsurance);

        Category car = createBlankCategory("Car", now, budgetTable, user);
        Category gas = createBlankCategory("Gas", now, budgetTable, user);
        Category carInsurance = createBlankCategory("Insurance", now, budgetTable, user);
        car.setMainOrder(2);
        categoryList.add(car);
        gas.setMainOrder(2);
        gas.setSubOrder(1);
        categoryList.add(gas);
        carInsurance.setMainOrder(2);
        carInsurance.setSubOrder(2);
        categoryList.add(carInsurance);

        Category food = createBlankCategory("Food", now, budgetTable, user);
        Category groceries = createBlankCategory("Groceries", now, budgetTable, user);
        Category eatingOut = createBlankCategory("Eating out", now, budgetTable, user);
        food.setMainOrder(3);
        categoryList.add(food);
        groceries.setMainOrder(3);
        groceries.setSubOrder(1);
        categoryList.add(groceries);
        eatingOut.setMainOrder(3);
        eatingOut.setSubOrder(2);
        categoryList.add(eatingOut);

        Category personal = createBlankCategory("Personal", now, budgetTable, user);
        Category fun = createBlankCategory("Fun Money", now, budgetTable, user);
        Category clothes = createBlankCategory("Clothes", now, budgetTable, user);
        Category hobbies = createBlankCategory("Hobbies", now, budgetTable, user);
        personal.setMainOrder(4);
        categoryList.add(personal);
        fun.setMainOrder(4);
        fun.setSubOrder(1);
        categoryList.add(fun);
        clothes.setMainOrder(4);
        clothes.setSubOrder(2);
        categoryList.add(clothes);
        hobbies.setMainOrder(4);
        hobbies.setSubOrder(3);
        categoryList.add(hobbies);

        categoryRepository.saveAll(categoryList);

        return budget;

    }

    public Category createBlankCategory(String name, LocalDateTime time, BudgetTable budgetTable, User user) {
        return Category.builder()
                .budgetTable(budgetTable)
                .centsActivity(0)
                .centsAssigned(0)
                .centsAvailable(0)
                .dollarAssigned(0)
                .dollarAvailable(0)
                .dollarActivity(0)
                .isCreditCard(false)
                .mainOrder(1)
                .subOrder(0)
                .createdDate(time)
                .updatedDate(time)
                .name(name)
                .user(user)
                .notes("")
                .build();
    }
}
