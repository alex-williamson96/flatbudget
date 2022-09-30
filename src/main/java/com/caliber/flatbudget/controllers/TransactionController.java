package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
}
