package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Category;
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
class PayeeRepositoryTest {

    @Autowired
    PayeeRepository payeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    static PostgreSQLContainer postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
            .withReuse(true);

    @BeforeEach
    public void setUp() {
        UserProfile user = new UserProfile();

        user.setFirstName("Test");
        user.setLastName("McMan");
        user.setEmail("Test.McMan@Budget.com");

        userRepository.saveAndFlush(user);

        UserProfile user1 = userRepository.findAll().get(0);

        for (int i = 0; i < 100; i++) {
            Payee payee = new Payee();
            payee.setName("place " + i);
            payee.setUserProfile(user1);
            payeeRepository.save(payee);
        }

    }

    @AfterEach
    public void tearDown() {
        List<UserProfile> userList = userRepository.findAll();

        for (UserProfile user : userList) {
            user.setBudgetList(null);
            user.setPayeeList(null);
            user.setTransactionList(null);
            userRepository.saveAndFlush(user);
        }

        List<Payee> payeeList = payeeRepository.findAll();

        for (Payee payee : payeeList) {
            payee.setUserProfile(null);
            payee.setTransactionList(null);
            payeeRepository.saveAndFlush(payee);
        }

        List<Transaction> transactionList = transactionRepository.findAll();

        for (Transaction transaction : transactionList) {
            transaction.setPayee(null);
            transaction.setUserProfile(null);
            transactionRepository.saveAndFlush(transaction);
        }

        List<Category> categoryList = categoryRepository.findAll();

        for (Category category : categoryList) {
            category.setBudget(null);
            category.setUserProfile(null);
            category.setTransactionList(null);
            categoryRepository.saveAndFlush(category);
        }

        transactionRepository.deleteAll();
        userRepository.deleteAll();
        payeeRepository.deleteAll();
    }

    @Test
    void findAllByUserProfileTest() {
        UserProfile user = userRepository.findAll().get(0);

        List<Payee> payeeList = payeeRepository.findAllByUserProfile(user);

        Assertions.assertEquals(100, payeeList.size(), "Payee size does not match.");
        Assertions.assertEquals(user, payeeList.get(0).getUserProfile(), "User does not match.");
    }

    @Test
    void createPayeeTest() {
        Payee payee1 = new Payee();
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        UserProfile user = userRepository.findAll().get(0);

        payee1.setName("payee name");
        payee1.setCreatedDate(time);
        payee1.setUpdatedDate(time);
        payee1.setUserProfile(user);

        payeeRepository.saveAndFlush(payee1);

        List<Payee> payeeList = payeeRepository.findAllByUserProfile(user);

        Payee newPayee = new Payee();

        for (Payee _payee : payeeList) {
            if (_payee.getName().equals("payee name")) {
                newPayee = _payee;
            }
        }

        Transaction transaction1 = new Transaction();
        transaction1.setPayee(newPayee);
        transaction1.setName("transaction");
        transaction1.setUserProfile(user);

        transactionRepository.saveAndFlush(transaction1);

        Transaction _transaction1 = transactionRepository.findAll().get(0);

        Category category1 = new Category();
        category1.setUserProfile(user);
        category1.setTransactionList(List.of(_transaction1));

        categoryRepository.saveAndFlush(category1);

        List<Payee> _payeeList = payeeRepository.findAllByUserProfile(user);

        Payee payee = new Payee();

        for (Payee __payee : _payeeList) {
            if (__payee.getName().equals("payee name")) {
                payee = __payee;
            }
        }

        Assertions.assertEquals("payee name", payee.getName(), "Payee name does not match.");
        Assertions.assertEquals(time, payee.getCreatedDate(), "Created date does not match.");
        Assertions.assertEquals(time, payee.getUpdatedDate(), "Updated date does not match.");
        Assertions.assertEquals(user, payee.getUserProfile(), "User does not match.");
        Assertions.assertEquals(Payee.class, payee.getClass(), "Payee class does not match.");
        Assertions.assertFalse(payee.equals(new Payee()));


    }
}