package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.AbstractIntegration;
import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.UserProfile;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class BudgetRepositoryTest extends AbstractIntegration {

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AccountRepository accountRepository;

    static GenericContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
            .withReuse(true);

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        postgresContainer.start();
        registry.add("spring.datasource.host", postgresContainer::getHost);
        registry.add("spring.datasource.port", postgresContainer::getFirstMappedPort);
    }

    @BeforeAll
    public static void beforeAll() {
        postgresContainer.start();
    }

    @BeforeEach
    public void setup() {
        UserProfile user1 = new UserProfile();
        UserProfile user2 = new UserProfile();
        UserProfile user3 = new UserProfile();

        List<UserProfile> userList = Arrays.asList(user1, user2, user3);
        int i = 1;
        for (UserProfile user : userList) {
            user.setFirstName("first" + i);
            user.setLastName("last" + i);
            user.setEmail("first.last." + i + "@test.com");
            user.setPassword("12345test" + i);
            user.setCreatedDate(LocalDateTime.now());
            user.setUpdatedDate(LocalDateTime.now());
            i++;
        }
        userRepository.saveAllAndFlush(userList);

        Budget budget1 = new Budget();
        Budget budget2 = new Budget();
        Budget budget3 = new Budget();

        budget1.setName("new budget");
        budget2.setName("budget after house");
        budget3.setName("budget from 2/5/2021");

        budget1.setUserProfile(user1);
        budget2.setUserProfile(user1);
        budget3.setUserProfile(user1);
        budgetRepository.saveAllAndFlush(Arrays.asList(budget1, budget2, budget3));

        user1.setBudgetList(budgetRepository.findAll());

        userRepository.saveAllAndFlush(userList);
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
            category.setTransactionList(null);
            categoryRepository.saveAndFlush(category);
        }

        List<Account> accountList = accountRepository.findAll();
        for (Account account : accountList) {
            account.setBudget(null);
            account.setUserProfile(null);
            account.setTransactionList(null);
            accountRepository.saveAndFlush(account);
        }

        accountRepository.deleteAll();
        categoryRepository.deleteAll();
        userRepository.deleteAll();
        budgetRepository.deleteAll();
    }

//    @Test
//    void findAllByUserProfileTest() {
//        UserProfile user = userRepository.findAll().get(0);
//
//        List<Budget> budgetList = budgetRepository.findAllByUserProfile(user);
//
//        Assertions.assertEquals(3, budgetList.size(), "Budget list sizes are different.");
//        Assertions.assertEquals(user.getUserId().toString(), budgetList.get(0).getUserProfile().getUserId().toString(), "Users are not the same.");
//        Assertions.assertNotSame(budgetList.get(1), budgetList.get(0), "Two of the budgets are the same.");
//        Assertions.assertNotSame(budgetList.get(2), budgetList.get(1), "Two of the budgets are the same.");
//    }
//
//    @Test
//    void accessBudgetTest() {
//        List<Budget> budgetList = budgetRepository.findAll();
//
//        Long id = budgetList.get(0).getBudgetId();
//
//        Budget budget = budgetRepository.findById(id).orElse(null);
//
//        Assertions.assertNotNull(budget, "Budget is null.");
//        Assertions.assertEquals(budgetList.get(0).getName(), budget.getName(), "Budgets are different");
//        Assertions.assertEquals(budgetList.get(0).getBudgetId(), budget.getBudgetId(), "Budgets are different");
//        Assertions.assertEquals(budgetList.get(0).getUserProfile().getUserId(), budget.getUserProfile().getUserId(), "Users of budget are different");
//        Assertions.assertNotEquals(budget.getName(), budgetList.get(1).getName());
//    }
//
//    @Test
//    void createBudget() {
//        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
//
//        UserProfile userProfile = new UserProfile();
//        userProfile.setFirstName("first");
//        userProfile.setLastName("last");
//        userRepository.saveAndFlush(userProfile);
//
//        UserProfile user = userRepository.findAll().get(0);
//
//        Budget newBudget = new Budget();
//        newBudget.setName("budget");
//        newBudget.setCreatedDate(time);
//        newBudget.setUpdatedDate(time);
//        newBudget.setUserProfile(user);
//
//        budgetRepository.saveAndFlush(newBudget);
//
//        Budget budget = budgetRepository.findAllByUserProfile(user).get(0);
//
//        for (int i = 0; i < 10; i++) {
//            Account newAccount = new Account();
//            newAccount.setBudget(budget);
//            accountRepository.saveAndFlush(newAccount);
//        }
//
//        userRepository.saveAndFlush(user);
//        budgetRepository.saveAndFlush(budget);
//        List<Budget> budgetList = budgetRepository.findAllByUserProfile(user);
//        Budget testBudget = budgetList.get(3);
//        List<Account> accountList = accountRepository.findAll();
//
//        Assertions.assertEquals(10, accountList.size(), "Number of accounts does not match.");
//        Assertions.assertEquals("budget", testBudget.getName(), "Budget names do not match.");
//        Assertions.assertEquals(time, testBudget.getUpdatedDate(), "Updated time does not match.");
//        Assertions.assertEquals(time, testBudget.getCreatedDate(), "Created time does not match.");
//        Assertions.assertEquals(user, testBudget.getUserProfile(), "User does not match.");
//        Assertions.assertEquals(String.class, testBudget.toString().getClass());
//        Assertions.assertFalse(testBudget.equals(new Budget()));
//    }


}