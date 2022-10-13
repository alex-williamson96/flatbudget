package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Payee;
import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.models.UserProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:tc:mysql:latest:///test",
})
class PayeeRepositoryTest {

    @Autowired
    PayeeRepository payeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    static GenericContainer<?> mySQLContainer = new GenericContainer<>(DockerImageName.parse("mysql:latest"))
            .withReuse(true);

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        mySQLContainer.start();
        registry.add("spring.mysql.host", mySQLContainer::getHost);
        registry.add("spring.mysql.port", mySQLContainer::getFirstMappedPort);
    }

    @BeforeEach
    public void setUp() {
        UserProfile user = new UserProfile();

        user.setFirstName("Test");
        user.setLastName("McMan");
        user.setEmail("Test.McMan@Budget.com");

        userRepository.saveAndFlush(user);

        UserProfile user1 = new UserProfile();

        if (userRepository.findById(1L).isPresent()) {
            user1 = userRepository.findById(1L).get();
        }


        for (int i = 0; i < 100; i++) {
            Payee payee = new Payee();
            payee.setName("place " + i);
            payee.setUserProfile(user1);
            payeeRepository.saveAndFlush(payee);
        }
    }

    @AfterEach
    public void tearDown() {
        List<UserProfile> userList = userRepository.findAll();

        for (UserProfile user : userList) {
            user.setBudgetList(null);
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

        transactionRepository.deleteAll();
        userRepository.deleteAll();
        payeeRepository.deleteAll();
    }

    @Test
    void findAllByUserProfileTest() throws Exception {
        if (userRepository.findById(Long.valueOf("1")).isEmpty()) {
            System.out.println("User not found!");
        }

        UserProfile user = userRepository.findById(1L).orElse(null);

        List<Payee> payeeList = payeeRepository.findAllByUserProfile(user);

        System.out.println(payeeList.size());
    }

    @Test
    void createPayeeTest() {

    }
}