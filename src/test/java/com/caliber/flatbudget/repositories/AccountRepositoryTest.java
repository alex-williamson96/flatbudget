package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @Before
    public void setup() {

    }

    @Test
    void findAccountsByUserAndBudget() {
        Budget budget = new Budget();
        budget.setId(0L);
        budgetRepository.saveAndFlush(budget);

        User user = new User();
        user.setFirstName("test");
        user.setLastName("case");
        user.setId(0L);
        user.setEmail("test.case@gmail.com");
        userRepository.saveAndFlush(user);

        List<User> userList = userRepository.findAll();
        System.out.println("=====================================");
        System.out.println(userList.size());
        for (User userO : userList) {
            System.out.println(userO.getFirstName());
        }

    }
}