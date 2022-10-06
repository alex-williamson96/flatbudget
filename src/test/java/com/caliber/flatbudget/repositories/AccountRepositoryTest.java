package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.AbstractIntegration;
import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.User;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

class AccountRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @BeforeEach
    public void setup() {
        Budget budget = new Budget();
        budgetRepository.saveAndFlush(budget);

        User user = new User();
        user.setFirstName("test");
        user.setLastName("case");
        user.setEmail("test.case@gmail.com");
        userRepository.saveAndFlush(user);

        Budget newBudget = budgetRepository.findAll().get(0);
        User newUser = userRepository.findAll().get(0);

        Account account1 = new Account();
        account1.setName("american express");
        account1.setDollar(0);
        account1.setCents(0);
        account1.setBudget(newBudget);
        account1.setUser(newUser);

        accountRepository.saveAndFlush(account1);


        Account account2 = new Account();
        account2.setName("wells fargo");
        account2.setDollar(0);
        account2.setCents(0);
        account2.setBudget(newBudget);
        account2.setUser(newUser);

        accountRepository.saveAndFlush(account2);


    }

    @Test
    void findAccountsByUserAndBudget() {

        User user = userRepository.findAll().get(0);
        Budget budget = budgetRepository.findAll().get(0);

        List<Account> accountList = accountRepository.findAccountsByUserAndBudget(user, budget);

        for (Account account : accountList) {
            System.out.println(account.getBudget().getId());
            System.out.println(account.getUser().getId());
            System.out.println(account.getId());
            System.out.println("=========================");
        }


    }
}