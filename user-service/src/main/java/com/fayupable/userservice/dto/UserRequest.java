package com.fayupable.userservice.dto;

import com.fayupable.userservice.entity.Address;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        Long id,
        @NotNull(message = "First name is required")
        String firstName,
        @NotNull(message = "Last name is required")
        String lastName,
        @NotNull(message = "Email is required")
        String email,
        Address address
) {
}
