package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.UserProfile;

public interface IUserService {

    UserProfile findById(Long id);

    UserProfile createUser(UserProfile userProfile);
}
