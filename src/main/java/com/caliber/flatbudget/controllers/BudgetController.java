package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.services.BudgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/budget")
@Slf4j
public class BudgetController {

    @Autowired
    private BudgetService budgetService;


}
