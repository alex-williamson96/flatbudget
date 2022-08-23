package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.IAccountService;
import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

@Slf4j
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        if (accountRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in accountRepository");
        }

        return accountRepository.findById(id).get();
    }

}
