package com.fayupable.restaurant.dto;

import com.fayupable.restaurant.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RestaurantRequest(
        String id,
        @NotNull(message = "Restaurant name is required")
        String name,
        @NotNull(message = "Restaurant description is required")
        String description,
        @NotNull(message = "Restaurant email is required")
        @Email(message = "Restaurant email is invalid")
        String email,
        @NotNull(message = "Restaurant contact number is required")
        String contactNumber,
        Address address
) {
}
