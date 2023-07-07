package com.caliber.flatbudget.services;

import com.caliber.flatbudget.iservices.IUserService;
import com.caliber.flatbudget.models.UserProfile;
import com.caliber.flatbudget.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<UserProfile> findAll() {
        return userRepository.findAll();
    }

    public UserProfile findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public UserProfile createUser(UserProfile userProfile) {
        UserProfile user = null;
        try {
            user = userRepository.save(userProfile);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return user;
    }
}
