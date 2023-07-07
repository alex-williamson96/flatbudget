package com.caliber.flatbudget.controllers;

import com.caliber.flatbudget.models.UserProfile;
import com.caliber.flatbudget.services.AuthService;
import com.caliber.flatbudget.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    private final UserService userService;

    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    public UserProfile getUserInfo() {
        UserProfile user = authService.getCurrentUserProfile();


        if (user == null) {
            DefaultOidcUser principal = authService.getPrincipal();
            return this.userService.createUser(UserProfile
                    .builder()
                    .email(principal.getEmail())
                    .firstName(principal.getGivenName())
                    .lastName(principal.getFamilyName())
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .currency("USD")
                    .currencyFormat("before")
                    .dateFormat("MM.DD.YYYY")
                    .build());
        }

        return user;
    }
}
