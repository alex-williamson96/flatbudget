package com.caliber.flatbudget.services.impls;

import com.caliber.flatbudget.dtos.User.UserMapperImpl;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.models.internal.request.SignupRequest;
import com.caliber.flatbudget.models.internal.response.Auth0UserResponse;
import com.caliber.flatbudget.models.security.ERole;
import com.caliber.flatbudget.models.security.Role;
import com.caliber.flatbudget.repositories.BudgetRepository;
import com.caliber.flatbudget.repositories.BudgetTableRepository;
import com.caliber.flatbudget.repositories.UserRepository;
import com.caliber.flatbudget.services.UserService;
import com.caliber.flatbudget.services.security.RoleServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapperImpl userMapper;
    private final UserRepository userRepository;
    private final RoleServiceImpl roleService;
    private final NewUserServiceImpl newUserService;
    private final WebClient auth0Client;

    public UserServiceImpl(UserRepository userRepository, RoleServiceImpl roleService, WebClient auth0Client,
                           UserMapperImpl userMapper, BudgetRepository budgetRepository, BudgetTableRepository budgetTableRepository, NewUserServiceImpl userService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.auth0Client = auth0Client;
        this.userMapper = userMapper;
        this.newUserService = userService;
    }

    @Value("${okta.auth0.mgmt.access.token}")
    private String accessToken;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User getUser(String userName) {
        User user = getUserInfoById(userName);

        if (!userRepository.existsByEmail(user.getEmail())) {
            return newUserService.createNewOAuthUser(user);
        }


        return userRepository.findByEmail(user.getEmail());
    }

    public User getUserInfoById(String userId) {
        return userMapper.auth0UserToUser(auth0Client
                .get()
                .uri("/api/v2/users/" + userId)
                .header("authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(Auth0UserResponse.class)
                .block(Duration.ofSeconds(2)));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User createUserFromSignUp(SignupRequest signupRequest) {
        Set<Role> roles = new HashSet<>();

        Role userRole = roleService.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: User Role is not found!"));
        roles.add(userRole);


        return saveUser(User.builder()
                .username(signupRequest.getUsername())
                .email(signupRequest.getEmail())
                .roles(roles)
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User newUser) {
        User user = null;
        try {
            user = userRepository.save(newUser);
        } catch (Exception e) {
            log.error("Error saving new user to database: " + e.getMessage());
        }

        return user;
    }

    @Override
    public Boolean checkUsernameAvailability(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean checkEmailAvailability(String email) {
        return userRepository.existsByUsername(email);
    }


}
