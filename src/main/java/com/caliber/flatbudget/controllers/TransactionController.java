package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.services.TransactionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@Slf4j
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionServiceImpl) {
        this.transactionService = transactionServiceImpl;
    }
}
