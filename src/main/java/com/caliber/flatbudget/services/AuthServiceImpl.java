package com.caliber.flatbudget.services;

import com.caliber.flatbudget.models.user.UserProfile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    private final UserServiceImpl userService;

    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserServiceImpl userServiceImpl, AuthenticationManager authenticationManager) {
        this.userService = userServiceImpl;
        this.authenticationManager = authenticationManager;
    }

    public UserProfile getCurrentUserProfile() {
        DefaultOidcUser principal = getPrincipal();
        if (getPrincipal() == null) {
            return null;
        }

        UserProfile user = userService.findByEmail(principal.getEmail());

        if (user == null) {
            return new UserProfile();
        }
        return user;
    }

    public DefaultOidcUser getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal.equals("anonymousUser")) {
            return null;
        }
        return (DefaultOidcUser) principal;
    }

//    public Authentication getAuthentification(String email) {
//        return authenticationManager.authenticate();
//    }

}
