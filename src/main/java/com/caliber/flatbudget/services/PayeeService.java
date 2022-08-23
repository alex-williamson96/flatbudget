package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.IPayeeService;
import com.caliber.flatbudget.models.Payee;
import com.caliber.flatbudget.repositories.PayeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayeeService implements IPayeeService {

    @Autowired
    private PayeeRepository payeeRepository;

    @Override
    public Payee findById(Long id) {
        if (payeeRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in payeeRepository");
        }

        return payeeRepository.findById(id).get();
    }

    @Override
    public void createPayee(Payee payee) {

        try {
            payeeRepository.save(payee);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
