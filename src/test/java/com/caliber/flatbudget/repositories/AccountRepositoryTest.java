package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.UserProfile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

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

    @BeforeAll
    public static void beforeAll() {
        mySQLContainer.start();
    }

    @BeforeEach
    public void setup() {
        Budget budget = new Budget();
        budgetRepository.saveAndFlush(budget);

        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName("test");
        userProfile.setLastName("case");
        userProfile.setEmail("test.case@gmail.com");
        userRepository.saveAndFlush(userProfile);

        Budget newBudget = budgetRepository.findAll().get(0);
        UserProfile newUserProfile = userRepository.findAll().get(0);

        Account account1 = new Account();
        account1.setName("american express");
        account1.setDollar(0);
        account1.setCents(0);
        account1.setBudget(newBudget);
        account1.setUserProfile(newUserProfile);

        accountRepository.saveAndFlush(account1);


        Account account2 = new Account();
        account2.setName("wells fargo");
        account2.setDollar(0);
        account2.setCents(0);
        account2.setBudget(newBudget);
        account2.setUserProfile(newUserProfile);

        accountRepository.saveAndFlush(account2);


    }

    @Test
    void findAccountsByUserProfileAndBudgetTest() {

        UserProfile userProfile = userRepository.findAll().get(0);
        Budget budget = budgetRepository.findAll().get(0);

        List<Account> accountList = accountRepository.findAccountsByUserProfileAndBudget(userProfile, budget);

        for (Account account : accountList) {
            System.out.println(account.getBudget().getId());
            System.out.println(account.getUserProfile().getId());
            System.out.println(account.getId());
            System.out.println("=========================");
        }


    }
}