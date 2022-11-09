package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.UserProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:tc:postgres:latest:///test",
})
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BudgetRepository budgetRepository;

    static GenericContainer<?> postgresContainer = new GenericContainer<>(DockerImageName.parse("postgres:latest"))
            .withReuse(true);

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        postgresContainer.start();
        registry.add("spring.postgres.host", postgresContainer::getHost);
        registry.add("spring.postgres.port", postgresContainer::getFirstMappedPort);
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

        userRepository.deleteAll();
        budgetRepository.deleteAll();
    }

    @Test
    void createUserTest() {
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        Budget newBudget = new Budget();
        newBudget.setCreatedDate(time);
        newBudget.setUpdatedDate(time);
        newBudget.setName("Test");

        budgetRepository.saveAndFlush(newBudget);

        Budget budget = budgetRepository.findAll().get(0);

        UserProfile newUserProfile = new UserProfile();

        newUserProfile.setFirstName("Test First Name");
        newUserProfile.setLastName("Test Last Name");
        newUserProfile.setEmail("first.last@test.com");
        newUserProfile.setCreatedDate(time);
        newUserProfile.setUpdatedDate(time);
        newUserProfile.setActiveBudget(budget.getId());
        newUserProfile.setDateFormat("MM.DD.YYYY");
        newUserProfile.setCurrencyFormat("before");
        newUserProfile.setCurrency("USD");

        userRepository.saveAndFlush(newUserProfile);

        budget.setUserProfile(newUserProfile);
        budgetRepository.saveAndFlush(budget);

        UserProfile user = userRepository.findAll().get(0);

        Assertions.assertEquals("Test First Name", user.getFirstName(), "First name does not match.");
        Assertions.assertEquals("Test Last Name", user.getLastName(), "Last name does not match.");
        Assertions.assertEquals("first.last@test.com", user.getEmail(), "Email does not match.");
        Assertions.assertEquals(time, user.getCreatedDate(), "Created date does not match.");
        Assertions.assertEquals(time, user.getUpdatedDate(), "Updated date does not match.");
        Assertions.assertEquals(budget.getId(), user.getActiveBudget(), "Active budget does not match.");
        Assertions.assertEquals("MM.DD.YYYY", user.getDateFormat(), "Date format does not match.");
        Assertions.assertEquals("before", user.getCurrencyFormat(), "Currency format does not match.");
        Assertions.assertEquals("USD", user.getCurrency(), "Currency does not match.");
        Assertions.assertEquals(String.class, user.toString().getClass());
        Assertions.assertFalse(user.equals(new UserProfile()));

    }


}