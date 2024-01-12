package com.caliber.flatbudget.services.impls;

import com.caliber.flatbudget.dtos.User.UserMapperImpl;
import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.models.internal.request.SignupRequest;
import com.caliber.flatbudget.models.internal.response.AccessTokenRefreshResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapperImpl userMapper;
    private final UserRepository userRepository;
    private final RoleServiceImpl roleService;
    private final NewUserServiceImpl newUserService;

    public UserServiceImpl(UserRepository userRepository, RoleServiceImpl roleService, UserMapperImpl userMapper,
                           BudgetRepository budgetRepository, BudgetTableRepository budgetTableRepository, NewUserServiceImpl userService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userMapper = userMapper;
        this.newUserService = userService;
    }

    @Value("${okta.oauth2.audience}")
    private String audience;

    @Value("${okta.oauth2.client-id}")
    private String clientId;

    @Value("${okta.oauth2.client-secret}")
    private String clientSecret;

    @Value("${okta.auth0.mgmt.access.audience}")
    private String mgmtAudience;

    @Value("${okta.oauth2.issuer}")
    private String issuer;


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
        RestClient authClient = RestClient.create();

        return userMapper.auth0UserToUser(authClient.get()
                .uri(issuer + "/api/v2/users/" + userId)
                .header("authorization", "Bearer " + Objects.requireNonNull(getAccessToken().getAccess_token()))
                .retrieve()
                .body(Auth0UserResponse.class));
    }

    public AccessTokenRefreshResponse getAccessToken() {
        RestClient authClient = RestClient.create();

        MultiValueMap<String, String> tokenData = new LinkedMultiValueMap<>();

        tokenData.add("client_id", clientId);
        tokenData.add("client_secret", clientSecret);
        tokenData.add("audience", mgmtAudience);
        tokenData.add("grant_type", "client_credentials");


        return authClient.post()
                .uri(issuer + "oauth/token")
                .header(APPLICATION_JSON)
                .body(tokenData)
                .retrieve()
                .body(AccessTokenRefreshResponse.class);
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

    public Long setActiveBudget(User user) {
        user.setActiveBudget(user.getBudgetList().get(0).getBudgetId());
        userRepository.save(user);

        return user.getActiveBudget();
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
