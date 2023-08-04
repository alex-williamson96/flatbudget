package com.caliber.flatbudget.services.security;

import com.caliber.flatbudget.models.internal.request.LoginRequest;
import org.springframework.security.core.Authentication;

public interface AuthService {
    Authentication getAuthentication(LoginRequest loginRequest);
}
