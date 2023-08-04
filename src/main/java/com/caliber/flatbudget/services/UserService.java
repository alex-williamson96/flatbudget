package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.models.internal.request.SignupRequest;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    User createUser(User user);

    Boolean checkUsernameAvailability(String username);

    Boolean checkEmailAvailability(String email);

    User saveUser(User user);

    User createUserFromSignUp(SignupRequest request);
}
