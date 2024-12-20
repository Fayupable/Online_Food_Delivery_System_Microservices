package com.fayupable.userservice.controller;

import com.fayupable.userservice.dto.UserRequest;
import com.fayupable.userservice.dto.UserResponse;
import com.fayupable.userservice.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(
            @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(
            @RequestBody @Valid UserRequest userRequest) {
        this.userService.updateUser(userRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        return ResponseEntity.ok(this.userService.findAllUsers());
    }

    @GetMapping("/exists/{user-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("user-id") String userId) {
        return ResponseEntity.ok(this.userService.existsById(userId));
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable("user-id") String userId) {
        return ResponseEntity.ok(this.userService.findUserById(userId));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user-id") String userId) {
        this.userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
