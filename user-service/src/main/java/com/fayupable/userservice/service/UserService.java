package com.fayupable.userservice.service;

import com.fayupable.userservice.dto.UserRequest;
import com.fayupable.userservice.dto.UserResponse;
import com.fayupable.userservice.entity.User;
import com.fayupable.userservice.exception.UserNotFoundException;
import com.fayupable.userservice.mapper.UserMapper;
import com.fayupable.userservice.repository.IUserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    public Long createUser(UserRequest userRequest) {
        var user = this.userRepository.save(this.userMapper.toUser(userRequest));
        return user.getId();
    }

    public void updateUser(UserRequest userRequest) {
        var user = this.userRepository.findById(Long.valueOf(userRequest.id()))
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", userRequest.id())));
        mergeUser(user, userRequest);
        this.userRepository.save(user);
    }

    private void mergeUser(User user, UserRequest userRequest) {
      if(StringUtils.isNotBlank(userRequest.firstName())) {
          user.setFirstName(userRequest.firstName());
      }
        if(StringUtils.isNotBlank(userRequest.lastName())) {
            user.setLastName(userRequest.lastName());
        }
        if(StringUtils.isNotBlank(userRequest.email())) {
            user.setEmail(userRequest.email());
        }
        if(userRequest.address() != null) {
            user.setAddress(userRequest.address());
        }
    }

    public List<UserResponse> findAllUsers(){
        return this.userRepository.findAll().stream()
                .map(this.userMapper::fromUser)
                .collect(Collectors.toList());
    }

    public UserResponse findUserById(String id){
        return this.userRepository.findById(Long.valueOf(id))
                .map(this.userMapper::fromUser)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", id)));
    }

    public boolean existsById(String id) {
        return this.userRepository.findById(Long.valueOf(id)).isPresent();
    }

    public void deleteUser(String id) {
        this.userRepository.deleteById(Long.valueOf(id));
    }
}
