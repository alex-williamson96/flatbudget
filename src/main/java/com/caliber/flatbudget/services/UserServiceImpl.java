package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.user.UserProfile;
import com.caliber.flatbudget.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
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

    public UserProfile save(UserProfile userProfile) {
        return userRepository.save(userProfile);
    }
}
