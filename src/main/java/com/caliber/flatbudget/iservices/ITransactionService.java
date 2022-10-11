package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.models.UserProfile;

import java.util.List;

public interface ITransactionService {

    Transaction findById(Long id);

    void createTransaction(Transaction transaction);

    void deleteTransaction(Transaction transaction);

    List<Transaction> findTransactionsByUserAndBudget(UserProfile userProfile, Budget budget);
}
