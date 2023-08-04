package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.models.User;

import java.util.List;

public interface TransactionService {

    Transaction findById(Long id);

    void createTransaction(Transaction transaction);

    void deleteTransaction(Transaction transaction);

    List<Transaction> findTransactionsByUserAndBudget(User user, Budget budget);
}
