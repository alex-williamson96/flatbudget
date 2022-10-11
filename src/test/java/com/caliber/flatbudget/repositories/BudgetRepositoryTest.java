package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.AbstractIntegration;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:tc:mysql:latest:///test",
})
class BudgetRepositoryTest extends AbstractIntegration {

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    UserRepository userRepository;

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
        UserProfile user1 = new UserProfile();
        UserProfile user2 = new UserProfile();
        UserProfile user3 = new UserProfile();

        List<UserProfile> userList = Arrays.asList(user1, user2, user3);
        int i = 1;
        for (UserProfile user : userList) {
            user.setFirstName("first" + i);
            user.setLastName("last" + i);
            user.setEmail("first.last." + i + "@test.com");
            user.setPassword("12345test" + i);
            user.setCreatedDate(LocalDateTime.now());
            user.setUpdatedDate(LocalDateTime.now());
            i++;
        }

        userRepository.saveAllAndFlush(userList);

        Budget budget1 = new Budget();
        Budget budget2 = new Budget();
        Budget budget3 = new Budget();

        budget1.setName("new budget");
        budget2.setName("budget after house");
        budget3.setName("budget from 2/5/2021");

        budget1.setUserProfile(user1);
        budget2.setUserProfile(user1);
        budget3.setUserProfile(user1);

        budgetRepository.saveAllAndFlush(Arrays.asList(budget1, budget2, budget3));

        user1.setBudgetList(budgetRepository.findAll());

        userRepository.saveAllAndFlush(userList);


    }

    @Test
    void findAllByUserProfileTest() {
        List<UserProfile> userList = userRepository.findAll();




    }
}