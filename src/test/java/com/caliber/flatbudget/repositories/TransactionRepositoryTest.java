package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Transaction;
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
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:tc:mysql:latest:///test",
})
class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BudgetRepository budgetRepository;

    static GenericContainer<?> mySQLContainer = new GenericContainer<>(DockerImageName.parse("mysql:latest"))
            .withReuse(true);

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        mySQLContainer.start();
        registry.add("spring.mysql.host", mySQLContainer::getHost);
        registry.add("spring.mysql.port", mySQLContainer::getFirstMappedPort);
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

        Budget budget = new Budget();

        budget.setName("new budget");

        budget.setUserProfile(user);

        budgetRepository.saveAndFlush(budget);
        userRepository.saveAndFlush(user);


        for (int i = 0; i < 50; i++) {
            Transaction transaction = new Transaction();
            transaction.setUserProfile(user);
            transaction.setBudget(budget);
            transaction.setName("transaction " + i);
            transactionRepository.saveAndFlush(transaction);
        }

        budgetRepository.saveAndFlush(budget);
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

        List<Transaction> transactionList = transactionRepository.findAll();

        for (Transaction transaction : transactionList) {
            transaction.setBudget(null);
            transaction.setUserProfile(null);
            transactionRepository.saveAndFlush(transaction);
        }

        transactionRepository.deleteAll();
        userRepository.deleteAll();
        budgetRepository.deleteAll();
    }

    @Test
    void findAllByUserProfileAndBudgetTest() {
        UserProfile user = userRepository.findAll().get(0);
        Budget budget = budgetRepository.findAll().get(0);

        List<Transaction> transactionList = transactionRepository.findAllByUserProfileAndBudget(user, budget);

        System.out.println("Number of transactions: " + transactionList.size());
        System.out.println("User Id: " + user.getId() + " vs " + transactionList.get(0).getUserProfile().getId());
        System.out.println("Budget Id: " + budget.getId() + " vs " + transactionList.get(0).getBudget().getId());

        Assertions.assertEquals(50, transactionList.size(), "Incorrect number of transactions.");
        Assertions.assertEquals(user.getId(), transactionList.get(0).getUserProfile().getId(), "User Ids do not match.");
        Assertions.assertEquals(budget.getId(), transactionList.get(0).getBudget().getId(), "Budget Ids do not match.");
    }
}