package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.Payee;
import com.caliber.flatbudget.models.user.UserProfile;
import com.caliber.flatbudget.repositories.PayeeRepository;
import com.caliber.flatbudget.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PayeeServiceImpl implements PayeeService {

    private final PayeeRepository payeeRepository;

    private final UserRepository userRepository;

    public PayeeServiceImpl(PayeeRepository payeeRepository, UserRepository userRepository) {
        this.payeeRepository = payeeRepository;
        this.userRepository = userRepository;
    }

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
