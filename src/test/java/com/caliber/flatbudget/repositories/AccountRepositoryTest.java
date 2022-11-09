package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Transaction;
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
import java.util.List;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    TransactionRepository transactionRepository;

    static PostgreSQLContainer postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
            .withReuse(true);

    @BeforeAll
    public static void beforeAll() {
        postgresContainer.start();
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

        Account account = accountRepository.findAll().get(0);

        for (int i = 0; i < 100; i++) {
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transactionRepository.saveAndFlush(transaction);
        }
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

        List<Account> accountList = accountRepository.findAll();

        for (Account account : accountList) {
            account.setBudget(null);
            account.setUserProfile(null);
            accountRepository.saveAndFlush(account);
        }

        List<Transaction> transactionList = transactionRepository.findAll();

        for (Transaction transaction : transactionList) {
            transaction.setAccount(null);
            transactionRepository.saveAndFlush(transaction);
        }

        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        userRepository.deleteAll();
        budgetRepository.deleteAll();
    }

    @Test
    void findAccountsByUserProfileAndBudgetTest() {

        UserProfile userProfile = userRepository.findAll().get(0);
        Budget budget = budgetRepository.findAll().get(0);

        List<Account> accountList = accountRepository.findAccountsByUserProfileAndBudget(userProfile, budget);

        Assertions.assertEquals(accountList.get(0).getBudget().getId(), budget.getId(), "Budget IDs do not match.");
        Assertions.assertEquals(accountList.get(0).getUserProfile().getId(), userProfile.getId(), "User IDs do not match.");
        Assertions.assertEquals(accountList.size(), 2, "Number of accounts is incorrect.");
        Assertions.assertEquals(accountList.get(0).getName(), "american express", "Account name does not match.");
    }

    @Test
    void createAccountTest() {
        Account account = new Account();

        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        account.setDollar(0);
        account.setCents(0);
        account.setName("account name");
        account.setOnBudget(true);
        account.setOrderPosition(1);
        account.setCreatedDate(time);
        account.setUpdatedDate(time);

        accountRepository.saveAndFlush(account);

        List<Account> accountList = accountRepository.findAll();
        Account foundAccount = new Account();

        for (Account _account : accountList) {
            if (_account.getName().equals("account name")) {
                foundAccount = _account;
            }
        }

        Assertions.assertEquals(account.getDollar(), foundAccount.getDollar(), "Dollar does not match.");
        Assertions.assertEquals(account.getCents(), foundAccount.getCents(), "Cents does not match.");
        Assertions.assertEquals(account.getName(), foundAccount.getName(), "Name does not match.");
        Assertions.assertTrue(foundAccount.getOnBudget(), "On budget is incorrect.");
        Assertions.assertEquals(1, foundAccount.getOrderPosition(), "Order position is incorrect.");
        Assertions.assertEquals(time, foundAccount.getCreatedDate(), "Created time is incorrect.");
        Assertions.assertEquals(time, foundAccount.getUpdatedDate(), "Updated time is incorrect");
        Assertions.assertEquals(String.class, foundAccount.toString().getClass());
        Assertions.assertFalse(foundAccount.equals(new Account()));
    }

    @Test
    void findAllTransactionsByAccount() {
        Account account = accountRepository.findAll().get(0);

        List<Transaction> transactionList = transactionRepository.findAllByAccount(account);

        Assertions.assertEquals(100, transactionList.size());
        Assertions.assertEquals(account, transactionList.get(0).getAccount());
    }
}