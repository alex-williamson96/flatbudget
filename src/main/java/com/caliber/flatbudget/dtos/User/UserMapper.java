package com.caliber.flatbudget.dtos.User;

import com.caliber.flatbudget.models.User;
import com.caliber.flatbudget.models.internal.response.Auth0UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    @Mapping(target = "username", source = "user_id")
    User auth0UserToUser(Auth0UserResponse auth0User);
}
