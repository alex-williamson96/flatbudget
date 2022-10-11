package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.AbstractIntegration;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:tc:mysql:latest:///test",
})
class BudgetRepositoryTest extends AbstractIntegration {

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

    @Autowired
    BudgetRepository budgetRepository;

    @Test
    void findAllByUser() {
    }
}