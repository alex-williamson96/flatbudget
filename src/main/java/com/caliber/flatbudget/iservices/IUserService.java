package com.caliber.flatbudget.iservices;

import com.caliber.flatbudget.models.User;

public interface IUserService {

    User findById(Long id);

    void createUser(User user);
}
