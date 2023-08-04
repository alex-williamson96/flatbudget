package com.caliber.flatbudget.models.internal.request;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nonnull;

@Getter
@Setter
public class SignupRequest {
    @Nonnull
    private String username;

    @Nonnull
    private String email;

    @Nonnull
    private String password;

    @Nonnull
    private String firstName;

    @Nonnull
    private String lastName;



}
