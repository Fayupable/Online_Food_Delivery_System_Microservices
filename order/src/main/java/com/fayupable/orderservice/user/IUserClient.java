package com.fayupable.orderservice.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "user-service",
        url = "${application.config.user-url}"
)
public interface IUserClient {
    @GetMapping("/{user-id}")
    Optional<UserResponse> findUserById(@PathVariable("user-id") String userId);
}
