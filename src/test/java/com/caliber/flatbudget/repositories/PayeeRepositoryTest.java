package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Category;
import com.caliber.flatbudget.models.Payee;
import com.caliber.flatbudget.models.Transaction;
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

    static GenericContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
            .withReuse(true);

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        postgresContainer.start();
        registry.add("spring.datasource.host", postgresContainer::getHost);
        registry.add("spring.datasource.port", postgresContainer::getFirstMappedPort);
    }

    @BeforeEach
    public void setUp() {
        User user = new User();

        user.setFirstName("Test");
        user.setLastName("McMan");
        user.setEmail("Test.McMan@Budget.com");

        userRepository.saveAndFlush(user);

        User user1 = userRepository.findAll().get(0);

        for (int i = 0; i < 100; i++) {
            Payee payee = new Payee();
            payee.setName("place " + i);
            payee.setUser(user1);
            payeeRepository.save(payee);
        }

    }

    @AfterEach
    public void tearDown() {
        List<User> userList = userRepository.findAll();

        for (User user : userList) {
            user.setBudgetList(null);
            user.setPayeeList(null);
            user.setTransactionList(null);
            userRepository.saveAndFlush(user);
        }

        List<Payee> payeeList = payeeRepository.findAll();

        for (Payee payee : payeeList) {
            payee.setUser(null);
            payee.setTransactionList(null);
            payeeRepository.saveAndFlush(payee);
        }

        List<Transaction> transactionList = transactionRepository.findAll();

        for (Transaction transaction : transactionList) {
            transaction.setPayee(null);
            transaction.setUser(null);
            transactionRepository.saveAndFlush(transaction);
        }

        List<Category> categoryList = categoryRepository.findAll();

        for (Category category : categoryList) {
            category.setUser(null);
            category.setTransactionList(null);
            categoryRepository.saveAndFlush(category);
        }

        transactionRepository.deleteAll();
        userRepository.deleteAll();
        payeeRepository.deleteAll();
    }
}