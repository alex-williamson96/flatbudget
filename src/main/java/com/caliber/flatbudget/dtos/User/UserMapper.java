package com.caliber.flatbudget.dtos.User;

import com.caliber.flatbudget.models.User;

public class UserMapper {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .build();
    }
}
