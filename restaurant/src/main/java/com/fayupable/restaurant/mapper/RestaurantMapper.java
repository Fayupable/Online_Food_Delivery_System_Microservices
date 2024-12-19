package com.fayupable.restaurant.mapper;

import com.fayupable.restaurant.dto.RestaurantRequest;
import com.fayupable.restaurant.dto.RestaurantResponse;
import com.fayupable.restaurant.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public Restaurant toRestaurant(RestaurantRequest request) {
        if (request == null) {
            return null;
        }
        return Restaurant.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .email(request.email())
                .contactNumber(request.contactNumber())
                .address(request.address())
                .build();
    }

    public RestaurantResponse fromRestaurant(Restaurant restaurant) {
        if (restaurant == null) {
            return null;
        }
        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getEmail(),
                restaurant.getContactNumber(),
                restaurant.getAddress()
        );
    }
}
