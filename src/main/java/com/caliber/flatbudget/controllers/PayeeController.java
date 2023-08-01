package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.services.PayeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payee")
@Slf4j
public class PayeeController {

    private final PayeeServiceImpl payeeService;

    public PayeeController(PayeeServiceImpl payeeServiceImpl) {
        this.payeeService = payeeServiceImpl;
    }
}
