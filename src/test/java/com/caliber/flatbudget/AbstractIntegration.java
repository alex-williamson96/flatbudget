package com.caliber.flatbudget;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:tc:postgres:latest:///test",
})
public class AbstractIntegration {

    static GenericContainer<?> postgresContainer = new GenericContainer<>(DockerImageName.parse("postgres:latest"))
            .withReuse(true);

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        postgresContainer.start();
        registry.add("spring.postgres.host", postgresContainer::getHost);
        registry.add("spring.postgres.port", postgresContainer::getFirstMappedPort);
    }

    @Test
    public void containerStarted() {
        Assertions.assertEquals("postgres:latest", postgresContainer.getDockerImageName(), "Docker Image name is incorrect.");
    }
}
