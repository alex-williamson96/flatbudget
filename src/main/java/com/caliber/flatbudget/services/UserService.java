package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.IUserService;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            log.error("Could not find entity " + id + " in userRepository");
        }

        return userRepository.findById(id).get();
    }

    @Override
    public void createUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
