package com.caliber.flatbudget.controllers.security;

import com.caliber.flatbudget.exceptions.TokenRefreshException;
import com.caliber.flatbudget.models.internal.request.LoginRequest;
import com.caliber.flatbudget.models.internal.request.SignupRequest;
import com.caliber.flatbudget.models.internal.response.JwtResponse;
import com.caliber.flatbudget.models.internal.response.MessageResponse;
import com.caliber.flatbudget.models.security.RefreshToken;

import com.caliber.flatbudget.services.impls.UserServiceImpl;
import com.caliber.flatbudget.services.security.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthServiceImpl authService;
    private final UserServiceImpl userService;

    @GetMapping(value = "/public")
    public String publicEndpoint() {
        return "All good. You DO NOT need to be authenticated to call /api/public.";
    }

    @GetMapping(value = "/private")
    public String privateEndpoint() {
        return "All good. You can see this because you are Authenticated.";
    }

    @GetMapping(value = "/private-scoped")
    public String privateScopedEndpoint() {
        return "All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope";
    }


}
