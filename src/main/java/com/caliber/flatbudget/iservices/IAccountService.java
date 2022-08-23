package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.Account;

public interface IAccountService {

    Account findById(Long id);
}
