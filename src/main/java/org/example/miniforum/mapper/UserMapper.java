package org.example.miniforum.mapper;

import org.example.miniforum.dto.request.UserRequest;
import org.example.miniforum.dto.response.UserResponse;
import org.example.miniforum.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest user);

    UserResponse toUserResponse(User user);
}
