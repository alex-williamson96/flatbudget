package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.user.UserProfile;

public interface UserService {

    UserProfile findById(Long id);

    UserProfile createUser(UserProfile userProfile);
}
