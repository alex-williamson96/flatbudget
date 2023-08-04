package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Payee;

public interface PayeeService {

    Payee findById(Long id);

    void createPayee(Payee payee, Long userId);
}
