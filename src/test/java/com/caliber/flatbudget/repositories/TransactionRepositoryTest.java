package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Payee;
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
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootTest
class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    PayeeRepository payeeRepository;

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

        Budget budget = new Budget();

        budget.setName("new budget");

        budget.setUserProfile(user);

        budgetRepository.saveAndFlush(budget);
        userRepository.saveAndFlush(user);

        Payee payee = new Payee();
        payeeRepository.saveAndFlush(payee);


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
            transaction.setPayee(null);
            transactionRepository.saveAndFlush(transaction);
        }

        List<Payee> payeeList = payeeRepository.findAll();

        for (Payee payee : payeeList) {
            payee.setTransactionList(null);
            payee.setUserProfile(null);
            payeeRepository.saveAndFlush(payee);
        }

        payeeRepository.deleteAll();
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

    @Test
    void createTransactionTest() {
        Transaction transaction = new Transaction();

        Budget budget = budgetRepository.findAll().get(0);

        Payee payee = payeeRepository.findAll().get(0);

        UserProfile user = userRepository.findAll().get(0);

        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        transaction.setName("Kroger");
        transaction.setDollar(100);
        transaction.setCents(0);
        transaction.setCreatedDate(time);
        transaction.setUpdatedDate(time);
        transaction.setIsOutflow(true);
        transaction.setIsPending(false);
        transaction.setNote("pull out cash for incidentals");
        transaction.setBudget(budget);
        transaction.setPayee(payee);
        transaction.setUserProfile(user);

        transactionRepository.saveAndFlush(transaction);

        List<Transaction> transactionList = transactionRepository.findAllByUserProfileAndBudget(user, budget);

        Transaction transaction1 = new Transaction();

        for (Transaction transactionLoop : transactionList) {
            if (transactionLoop.getName().equals("Kroger")) {
                transaction1 = transactionLoop;
            }
        }

        Assertions.assertEquals("Kroger", transaction1.getName());
        Assertions.assertEquals(user, transaction1.getUserProfile());
        Assertions.assertEquals(budget, transaction1.getBudget());
        Assertions.assertEquals(100, transaction1.getDollar());
        Assertions.assertEquals(0, transaction1.getCents());
        Assertions.assertEquals(time, transaction1.getCreatedDate());
        Assertions.assertEquals(time, transaction1.getUpdatedDate());
        Assertions.assertTrue(transaction1.getIsOutflow());
        Assertions.assertFalse(transaction1.getIsPending());
        Assertions.assertEquals(payee, transaction1.getPayee());
        Assertions.assertEquals(String.class, transaction1.toString().getClass());
        Assertions.assertNotEquals(transaction1, new Transaction());
    }
}