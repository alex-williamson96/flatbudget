package com.caliber.flatbudget.dtos.User;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long userId;

    private String username;

    private String firstName;

    private String lastName;

    private String email;
}
