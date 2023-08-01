package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.models.user.UserProfile;

import java.util.List;

public interface TransactionService {

    Transaction findById(Long id);

    void createTransaction(Transaction transaction);

    void deleteTransaction(Transaction transaction);

    List<Transaction> findTransactionsByUserAndBudget(UserProfile userProfile, Budget budget);
}
