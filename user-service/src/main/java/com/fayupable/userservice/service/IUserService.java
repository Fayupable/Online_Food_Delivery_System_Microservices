package com.fayupable.userservice.service;

import com.fayupable.userservice.dto.UserRequest;
import com.fayupable.userservice.dto.UserResponse;

import java.util.List;

public interface IUserService {
    Long createUser(UserRequest userRequest);

    void updateUser(UserRequest userRequest);

    List<UserResponse> findAllUsers();

    UserResponse findUserById(String id);

    boolean existsById(String id);

    void deleteUser(String id);
}
