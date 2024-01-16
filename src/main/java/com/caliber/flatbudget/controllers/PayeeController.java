package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.services.impls.PayeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payee")
@Slf4j
@CrossOrigin("*")
public class PayeeController {

    private final PayeeServiceImpl payeeService;

    public PayeeController(PayeeServiceImpl payeeService) {
        this.payeeService = payeeService;
    }
}
