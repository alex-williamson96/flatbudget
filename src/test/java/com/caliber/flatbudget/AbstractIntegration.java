package com.caliber.flatbudget;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.datasource.url=jdbc:tc:mysql:latest:///test",
})
public class AbstractIntegration {

    static GenericContainer<?> mysql = new GenericContainer<>(DockerImageName.parse("mysql:latest"))
            .withExposedPorts(5379);

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        mysql.start();
        registry.add("spring.mysql.host", mysql::getHost);
        registry.add("spring.mysql.port", mysql::getFirstMappedPort);
    }
}
