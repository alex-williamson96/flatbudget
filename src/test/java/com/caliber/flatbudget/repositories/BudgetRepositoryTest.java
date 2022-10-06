package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.AbstractIntegration;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class BudgetRepositoryTest extends AbstractIntegration {

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void findAllByUser() {
    }
}