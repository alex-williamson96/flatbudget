package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findAllByUser(User user);

    List<Budget> findAllByNameAndUser(String name, User user);

    Budget findByNameAndUser(String name, User user);

}
