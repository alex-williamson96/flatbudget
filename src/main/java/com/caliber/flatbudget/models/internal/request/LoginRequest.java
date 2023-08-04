package com.caliber.flatbudget.models.internal.request;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nonnull;

@Getter
@Setter
public class LoginRequest {
    @Nonnull
    private String username;

    @Nonnull
    private String password;

}
