package com.caliber.flatbudget.models.internal.response;

import lombok.Getter;

@Getter
public class AccessTokenRefreshResponse {
    private String bearer;
    private String access_token;
    private String expiresIn;
    private String scope;
}
