package com.solbeg.capco.mapper;

import com.solbeg.capco.dto.UserRequest;
import com.solbeg.capco.dto.UserResponse;
import com.solbeg.capco.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toEntity(UserRequest source);
    UserResponse toResponse(User user);
}
