package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.UserProfile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public UserProfile getCurrentUserProfile() {
        return userService.findByEmail(getPrincipal().getEmail());
    }

    public DefaultOidcUser getPrincipal() {
        return (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
