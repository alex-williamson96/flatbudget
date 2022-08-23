package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
