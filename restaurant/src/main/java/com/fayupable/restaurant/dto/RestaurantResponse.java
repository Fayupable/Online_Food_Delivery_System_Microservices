package com.fayupable.restaurant.dto;

import com.fayupable.restaurant.entity.Address;

public record RestaurantResponse(
        String id,
        String name,
        String description,
        String email,
        String contactNumber,
        Address address
) {
}
