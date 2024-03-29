package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.models.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@SpringBootTest
class AccountRepositoryTest {


    AccountRepository accountRepository;

    UserRepository userRepository;

    BudgetRepository budgetRepository;

    TransactionRepository transactionRepository;

    @Autowired
    public AccountRepositoryTest(AccountRepository accountRepository, UserRepository userRepository, BudgetRepository budgetRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
        this.transactionRepository = transactionRepository;
    }

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

        Account account = accountRepository.findAll().get(0);

        for (int i = 0; i < 100; i++) {
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transactionRepository.saveAndFlush(transaction);
        }
    }

    @AfterEach
    public void tearDown() {
        List<User> userList = userRepository.findAll();

        for (User user : userList) {
            user.setBudgetList(null);
            userRepository.saveAndFlush(user);
        }

        List<Budget> budgetList = budgetRepository.findAll();

        for (Budget budget : budgetList) {
            budget.setUser(null);
            budgetRepository.saveAndFlush(budget);
        }

        List<Account> accountList = accountRepository.findAll();

        for (Account account : accountList) {
            account.setBudget(null);
            account.setUser(null);
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

//    @Test
//    void findAccountsByBudgetTest() {
//
//        UserProfile userProfile = userRepository.findAll().get(0);
//        Budget budget = budgetRepository.findAll().get(0);
//
//        List<Account> accountList = accountRepository.findAccountsByBudget(budget);
//
//        Assertions.assertEquals(accountList.get(0).getBudget().getBudgetId(), budget.getBudgetId(), "Budget IDs do not match.");
//        Assertions.assertEquals(accountList.get(0).getUserProfile().getUserId(), userProfile.getUserId(), "User IDs do not match.");
//        Assertions.assertEquals(accountList.size(), 2, "Number of accounts is incorrect.");
//        Assertions.assertEquals(accountList.get(0).getName(), "american express", "Account name does not match.");
//    }
//
//    @Test
//    void createAccountTest() {
//        Account account = new Account();
//
//        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
//
//        account.setDollar(0);
//        account.setCents(0);
//        account.setName("account name");
//        account.setOnBudget(true);
//        account.setOrderPosition(1);
//        account.setCreatedDate(time);
//        account.setUpdatedDate(time);
//
//        accountRepository.saveAndFlush(account);
//
//        List<Account> accountList = accountRepository.findAll();
//        Account foundAccount = new Account();
//
//        for (Account _account : accountList) {
//            if (_account.getName().equals("account name")) {
//                foundAccount = _account;
//            }
//        }
//
//        Assertions.assertEquals(account.getDollar(), foundAccount.getDollar(), "Dollar does not match.");
//        Assertions.assertEquals(account.getCents(), foundAccount.getCents(), "Cents does not match.");
//        Assertions.assertEquals(account.getName(), foundAccount.getName(), "Name does not match.");
//        Assertions.assertTrue(foundAccount.getOnBudget(), "On budget is incorrect.");
//        Assertions.assertEquals(1, foundAccount.getOrderPosition(), "Order position is incorrect.");
//        Assertions.assertEquals(time, foundAccount.getCreatedDate(), "Created time is incorrect.");
//        Assertions.assertEquals(time, foundAccount.getUpdatedDate(), "Updated time is incorrect");
//        Assertions.assertEquals(String.class, foundAccount.toString().getClass());
//        Assertions.assertNotEquals(foundAccount, new Account());
//    }
//
//    @Test
//    void findAllTransactionsByAccount() {
//        Account account = accountRepository.findAll().get(0);
//
//        List<Transaction> transactionList = transactionRepository.findAllByAccount(account);
//
//        Assertions.assertEquals(100, transactionList.size());
//        Assertions.assertEquals(account, transactionList.get(0).getAccount());
//    }
}