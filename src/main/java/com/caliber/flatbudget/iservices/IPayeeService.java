package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.Payee;

public interface IPayeeService {

    Payee findById(Long id);

    void createPayee(Payee payee, Long userProfileId);
}
