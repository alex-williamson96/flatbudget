package com.caliber.flatbudget.models.internal.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String tokenType = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
