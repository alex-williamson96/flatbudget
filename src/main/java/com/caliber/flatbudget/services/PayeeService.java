package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.IPayeeService;
import com.caliber.flatbudget.models.Payee;
import com.caliber.flatbudget.models.UserProfile;
import com.caliber.flatbudget.repositories.PayeeRepository;
import com.caliber.flatbudget.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PayeeService implements IPayeeService {

    @Autowired
    private PayeeRepository payeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Payee findById(Long id) {
        if (payeeRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in payeeRepository");
        }

        return payeeRepository.findById(id).get();
    }

    @Override
    public void createPayee(Payee payee, Long userProfileId) {
        if (userRepository.findById(userProfileId).isEmpty()) {
            log.error("Can't find entity UserProfile with id: " + userProfileId);
        }

        UserProfile userProfile = userRepository.findById(userProfileId).get();
        payee.setCreatedDate(LocalDateTime.now());
        payee.setCreatedDate(LocalDateTime.now());
        payee.setUserProfile(userProfile);
        try {
            payeeRepository.save(payee);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
