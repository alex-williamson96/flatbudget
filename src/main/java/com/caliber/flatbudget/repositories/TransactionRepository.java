package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUser(User user);
}
