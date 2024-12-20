package com.fayupable.userservice.dto;

import com.fayupable.userservice.entity.Address;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
