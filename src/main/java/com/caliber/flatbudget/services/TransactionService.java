package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.ITransactionService;
import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction findById(Long id) {
        if (transactionRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in transactionRepository");
        }

        return transactionRepository.findById(id).get();
    }

    public void createTransaction(Transaction transaction) {
        try {
            transactionRepository.save(transaction);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
