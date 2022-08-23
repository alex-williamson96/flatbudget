package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.Transaction;

public interface ITransactionService {

    Transaction findById(Long id);

    void createTransaction(Transaction transaction);
}
