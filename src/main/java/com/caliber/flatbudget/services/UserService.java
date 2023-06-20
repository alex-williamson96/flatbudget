package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.IUserService;
import com.caliber.flatbudget.models.UserProfile;
import com.caliber.flatbudget.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserProfile findById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in userRepository");
        }

        return userRepository.findById(id).get();
    }

    @Override
    public void createUser(UserProfile userProfile) {
        try {
            userRepository.save(userProfile);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
