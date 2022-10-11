package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.UserProfile;

public interface IUserService {

    UserProfile findById(Long id);

    void createUser(UserProfile userProfile);
}
