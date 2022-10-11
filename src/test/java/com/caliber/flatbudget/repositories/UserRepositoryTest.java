package com.caliber.flatbudget.repositories;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:tc:mysql:latest:///test",
})
class UserRepositoryTest {

    static GenericContainer<?> mySQLContainer = new GenericContainer<>(DockerImageName.parse("mysql:latest"))
            .withReuse(true);

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        mySQLContainer.start();
        registry.add("spring.mysql.host", mySQLContainer::getHost);
        registry.add("spring.mysql.port", mySQLContainer::getFirstMappedPort);
    }


}