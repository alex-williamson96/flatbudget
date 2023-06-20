package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.ITransactionService;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.models.UserProfile;
import com.caliber.flatbudget.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction findById(Long id) {
        if (transactionRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in transactionRepository");
        }

        return transactionRepository.findById(id).get();
    }

    @Override
    public void createTransaction(Transaction transaction) {
        try {
            transactionRepository.save(transaction);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        try {
            transactionRepository.delete(transaction);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public List<Transaction> findTransactionsByUserAndBudget(UserProfile userProfile, Budget budget) {
        return transactionRepository.findAllByUserProfileAndBudget(userProfile, budget);
    }

}
