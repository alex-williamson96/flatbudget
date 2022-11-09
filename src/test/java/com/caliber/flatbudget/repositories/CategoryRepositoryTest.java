package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.UserProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    UserRepository userRepository;

    static GenericContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
            .withReuse(true);

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        postgresContainer.start();
        registry.add("spring.datasource.host", postgresContainer::getHost);
        registry.add("spring.datasource.port", postgresContainer::getFirstMappedPort);
    }

    @BeforeEach
    public void setup() {
        UserProfile user = new UserProfile();

        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first.last@test.com");
        user.setPassword("12345test");
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());

        userRepository.saveAndFlush(user);


        Budget budget1 = new Budget();

        budget1.setName("new budget");
        budget1.setUserProfile(user);
        budgetRepository.saveAndFlush(budget1);

        Category category1 = new Category();
        Category category2 = new Category();
        Category category3 = new Category();
        Category category4 = new Category();
        Category category5 = new Category();

        int j = 0;
        for (Category category : Arrays.asList(category1, category2, category3, category4, category5)) {
            category.setName("Category " + j);
            category.setUserProfile(user);
            category.setBudget(budget1);
            j++;
            categoryRepository.saveAndFlush(category);
        }
        budgetRepository.saveAndFlush(budget1);
        userRepository.saveAndFlush(user);
    }

    @AfterEach
    public void tearDown() {
        List<UserProfile> userList = userRepository.findAll();

        for (UserProfile user : userList) {
            user.setBudgetList(null);
            userRepository.saveAndFlush(user);
        }

        List<Budget> budgetList = budgetRepository.findAll();

        for (Budget budget : budgetList) {
            budget.setUserProfile(null);
            budgetRepository.saveAndFlush(budget);
        }

        List<Category> categoryList = categoryRepository.findAll();
        for (Category category : categoryList) {
            category.setUserProfile(null);
            category.setBudget(null);
            categoryRepository.saveAndFlush(category);
        }

        categoryRepository.deleteAll();
        userRepository.deleteAll();
        budgetRepository.deleteAll();
    }

    @Test
    void findAllByBudgetTest() {
        UserProfile user = userRepository.findAll().get(0);
        Budget budget = budgetRepository.findBudgetByNameAndUserProfile("new budget", user);

        List<Category> categoryList = categoryRepository.findAllByBudget(budget);

        Assertions.assertEquals(categoryList.size(), 5, "More categories than expected.");
        Assertions.assertEquals(categoryList.get(0).getBudget().getId(), budget.getId(), "Budget IDs do not match.");
        Assertions.assertEquals(categoryList.get(0).getUserProfile().getId(), user.getId(), "User IDs do not match.");
    }

    @Test
    void createCategoryTest() {
        UserProfile user1 = userRepository.findAll().get(0);
        Budget budget1 = budgetRepository.findAll().get(0);

        Category newCategory = new Category();

        newCategory.setName("House");
        newCategory.setDollarAssigned(100);
        newCategory.setCentsAssigned(0);
        newCategory.setDollarAvailable(75);
        newCategory.setCentsAvailable(0);
        newCategory.setDollarActivity(25);
        newCategory.setCentsActivity(0);
        newCategory.setMainOrder(1);
        newCategory.setSubOrder(0);
        newCategory.setUserProfile(user1);
        newCategory.setBudget(budget1);
        newCategory.setIsCreditCard(false);

        categoryRepository.saveAndFlush(newCategory);

        List<Category> categoryList= categoryRepository.findAllByBudget(budget1);

        Category category = new Category();

        for (Category cat : categoryList) {
            if (cat.getName().equals("House")) {
                category = cat;
            }
        }

        Assertions.assertEquals("House", category.getName(), "Category name does not match.");
        Assertions.assertEquals(100, category.getDollarAssigned(), "Dollars assigned does not match.");
        Assertions.assertEquals(0, category.getCentsAssigned(), "Cents assigned does not match.");
        Assertions.assertEquals(75, category.getDollarAvailable(), "Dollars activity does not match.");
        Assertions.assertEquals(0, category.getCentsAvailable(), "Cents activity does not match.");
        Assertions.assertEquals(25, category.getDollarActivity(), "Dollars available does not match.");
        Assertions.assertEquals(0, category.getCentsActivity(), "Cents available does not match.");
        Assertions.assertEquals(1, category.getMainOrder(), "Main order does not match.");
        Assertions.assertEquals(0, category.getSubOrder(), "Sub order does not match");
        Assertions.assertEquals(user1, category.getUserProfile(), "User does not match.");
        Assertions.assertEquals(budget1, category.getBudget(), "Budget does not match.");
        Assertions.assertEquals(false, category.getIsCreditCard());
        Assertions.assertEquals(String.class, category.toString().getClass());
        Assertions.assertFalse(category.equals(new Category()));
    }
}