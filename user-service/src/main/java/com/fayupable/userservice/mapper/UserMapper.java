package com.fayupable.userservice.mapper;

import com.fayupable.userservice.dto.UserRequest;
import com.fayupable.userservice.dto.UserResponse;
import com.fayupable.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        return User.builder()
                .id(userRequest.id())
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .email(userRequest.email())
                .address(userRequest.address())
                .build();
    }

    public UserResponse fromUser(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAddress()
        );
    }
}
