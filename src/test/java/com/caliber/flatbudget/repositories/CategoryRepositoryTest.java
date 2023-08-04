package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
        User user = new User();

        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("first.last@test.com");
        user.setPassword("12345test");
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());

        userRepository.saveAndFlush(user);


        Budget budget1 = new Budget();

        budget1.setName("new budget");
        budget1.setUser(user);
        budgetRepository.saveAndFlush(budget1);

        Category category1 = new Category();
        Category category2 = new Category();
        Category category3 = new Category();
        Category category4 = new Category();
        Category category5 = new Category();

        int j = 0;
        for (Category category : Arrays.asList(category1, category2, category3, category4, category5)) {
            category.setName("Category " + j);
            category.setUser(user);
            j++;
            categoryRepository.saveAndFlush(category);
        }
        budgetRepository.saveAndFlush(budget1);
        userRepository.saveAndFlush(user);
    }

    @AfterEach
    public void tearDown() {
        List<User> userList = userRepository.findAll();

        for (User user : userList) {
            user.setBudgetList(null);
            userRepository.saveAndFlush(user);
        }

        List<Budget> budgetList = budgetRepository.findAll();

        for (Budget budget : budgetList) {
            budget.setUser(null);
            budgetRepository.saveAndFlush(budget);
        }

        List<Category> categoryList = categoryRepository.findAll();
        for (Category category : categoryList) {
            category.setUser(null);
            categoryRepository.saveAndFlush(category);
        }

        categoryRepository.deleteAll();
        userRepository.deleteAll();
        budgetRepository.deleteAll();
    }
}